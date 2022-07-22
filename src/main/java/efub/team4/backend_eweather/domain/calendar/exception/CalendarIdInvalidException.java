package efub.team4.backend_eweather.domain.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CalendarIdInvalidException extends ResponseStatusException {

    public CalendarIdInvalidException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}
