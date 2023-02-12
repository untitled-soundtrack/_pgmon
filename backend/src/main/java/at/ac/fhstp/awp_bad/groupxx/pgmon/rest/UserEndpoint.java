package at.ac.fhstp.awp_bad.groupxx.pgmon.rest;

import at.ac.fhstp.awp_bad.groupxx.pgmon.auth.TokenService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.*;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.UserService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {
    private final UserService userService;
    private final TokenService tokenService;

    public UserEndpoint(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/register")
    public JwtDto registerUser(@RequestBody UserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getName());
        user.setPassword(userDto.getPassword());

        try {
            userService.addUser(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists.", e);
        }

        JwtDto jwtDto = new JwtDto();
        jwtDto.setAuthToken(tokenService.generateToken(user));
        return jwtDto;
    }

    @PostMapping(value = "/login")
    public JwtDto login(@RequestBody LoginDto loginDto) {

        try {
            return userService.login(loginDto.getEmail(), loginDto.getPassword());

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials!");
        }
    }

    @PatchMapping("/details")
    public JwtDto updateUser(@RequestBody UserUpdateDto userUpdateDto) {

        try {
            return userService.updateUser(userUpdateDto);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad credentials!");
        }
    }

    @GetMapping("/details")
    public UserDetailDto userDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setEmail(user.getEmail());
        userDetailDto.setName(user.getUsername());

        return userDetailDto;

    }
}
