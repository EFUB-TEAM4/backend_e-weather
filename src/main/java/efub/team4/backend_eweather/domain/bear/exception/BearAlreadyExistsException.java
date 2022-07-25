package efub.team4.backend_eweather.domain.bear.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class BearAlreadyExistsException extends DataIntegrityViolationException {
    public BearAlreadyExistsException(String message) { super(message); }
}
