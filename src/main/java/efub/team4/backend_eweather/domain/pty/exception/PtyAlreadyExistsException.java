package efub.team4.backend_eweather.domain.pty.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class PtyAlreadyExistsException extends DataIntegrityViolationException {
    public PtyAlreadyExistsException(String message) {
        super(message);
    }
}
