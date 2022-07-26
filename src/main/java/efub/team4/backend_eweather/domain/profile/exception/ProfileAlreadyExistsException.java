package efub.team4.backend_eweather.domain.profile.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ProfileAlreadyExistsException extends DataIntegrityViolationException {
    public ProfileAlreadyExistsException(String message){
        super(message);
    }
}
