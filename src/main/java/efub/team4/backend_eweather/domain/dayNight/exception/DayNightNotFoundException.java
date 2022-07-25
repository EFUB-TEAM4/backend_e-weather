package efub.team4.backend_eweather.domain.dayNight.exception;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class DayNightNotFoundException extends ResourceNotFoundException {
    public DayNightNotFoundException(String message) {
        super(message);
    }
}
