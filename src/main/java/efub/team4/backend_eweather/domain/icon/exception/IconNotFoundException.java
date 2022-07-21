package efub.team4.backend_eweather.domain.icon.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class IconNotFoundException extends ResourceNotFoundException {
    public IconNotFoundException(String message) {
        super(message);
    }
}
