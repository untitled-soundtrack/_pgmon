package at.ac.fhstp.awp_bad.groupxx.pgmon.service.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
