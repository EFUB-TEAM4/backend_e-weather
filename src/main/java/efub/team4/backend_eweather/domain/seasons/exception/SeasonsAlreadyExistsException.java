package efub.team4.backend_eweather.domain.seasons.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SeasonsAlreadyExistsException extends DataIntegrityViolationException {
    public SeasonsAlreadyExistsException(String message) {
        super(message);
    }
}
