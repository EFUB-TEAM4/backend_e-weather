package efub.team4.backend_eweather.domain.calendar.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CalendarAlreadyExistsException extends DataIntegrityViolationException {
    public CalendarAlreadyExistsException(String message) {
        super(message);
    }
}
