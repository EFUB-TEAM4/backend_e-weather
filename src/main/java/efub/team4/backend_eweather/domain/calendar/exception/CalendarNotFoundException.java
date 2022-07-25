package efub.team4.backend_eweather.domain.calendar.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class CalendarNotFoundException extends ResourceNotFoundException {
    public CalendarNotFoundException(String message) {
        super(message);
    }
}
