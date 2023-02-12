package at.ac.fhstp.awp_bad.groupxx.pgmon.service.impl;

import at.ac.fhstp.awp_bad.groupxx.pgmon.auth.TokenService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.JwtDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.UserUpdateDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.UserRepository;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.UserService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.exceptions.UserAlreadyExistsException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {

        if (userRepository.findByEmailIgnoreCase(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("User " + user.getEmail() + " already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public JwtDto login(String email, String pwd) throws BadCredentialsException {
        User user = userRepository.findByEmailIgnoreCase(email);

        if (user == null || !passwordEncoder.matches(pwd, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials!");
        }

        JwtDto jwtDto = new JwtDto();
        jwtDto.setAuthToken(tokenService.generateToken(user));

        return jwtDto;
    }

    @Override
    public JwtDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByEmailIgnoreCase(userUpdateDto.getEmail());

        if (user != null) {

            if (userUpdateDto.getName() != null) {
                user.setUsername(userUpdateDto.getName());
            }
            if (userUpdateDto.getPassword() != null && userUpdateDto.getPasswordNew() != null) {
                if (!passwordEncoder.matches(userUpdateDto.getPassword(), user.getPassword())) {
                    throw new BadCredentialsException("Incorrect Password!");
                }

                user.setPassword(passwordEncoder.encode(userUpdateDto.getPasswordNew()));
            }

            userRepository.save(user);

            JwtDto jwtDto = new JwtDto();
            jwtDto.setAuthToken(tokenService.generateToken(user));
            return jwtDto;
        }

        throw new BadCredentialsException("Bad credentials");
    }
}
