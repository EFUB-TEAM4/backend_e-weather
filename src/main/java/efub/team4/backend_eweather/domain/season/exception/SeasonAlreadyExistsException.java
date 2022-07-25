package efub.team4.backend_eweather.domain.season.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SeasonAlreadyExistsException extends DataIntegrityViolationException {
    public SeasonAlreadyExistsException(String message) {
        super(message);
    }
}
