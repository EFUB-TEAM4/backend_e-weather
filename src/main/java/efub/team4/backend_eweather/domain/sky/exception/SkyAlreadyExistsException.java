package efub.team4.backend_eweather.domain.sky.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SkyAlreadyExistsException extends DataIntegrityViolationException {
    public SkyAlreadyExistsException(String message) {
        super(message);
    }
}
