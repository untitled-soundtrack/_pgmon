package at.ac.fhstp.awp_bad.groupxx.pgmon.service;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.JwtDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.UserUpdateDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.exceptions.UserAlreadyExistsException;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistsException;

    JwtDto login(String email, String pwd) throws BadCredentialsException;

    JwtDto updateUser(UserUpdateDto userUpdateDto) throws BadCredentialsException;
}
