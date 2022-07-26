package efub.team4.backend_eweather.domain.dayNight.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DayNightAlreadyExistsException extends DataIntegrityViolationException {
    public DayNightAlreadyExistsException(String message) {
        super(message);
    }
}
