package efub.team4.backend_eweather.domain.bear.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class BearNotFoundException extends ResourceNotFoundException {
    public BearNotFoundException(String message) { super(message);}
}
