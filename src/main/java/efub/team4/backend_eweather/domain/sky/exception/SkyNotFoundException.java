package efub.team4.backend_eweather.domain.sky.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SkyNotFoundException extends ResourceNotFoundException {
    public SkyNotFoundException(String message) {
        super(message);
    }
}
