package efub.team4.backend_eweather.domain.icon.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class IconAlreadyExistsException extends DataIntegrityViolationException {
    public IconAlreadyExistsException(String message) {
        super(message);
    }
}
