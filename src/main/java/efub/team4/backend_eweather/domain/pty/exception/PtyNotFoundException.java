package efub.team4.backend_eweather.domain.pty.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class PtyNotFoundException extends ResourceNotFoundException {
    public PtyNotFoundException(String message){
        super(message);
    }
}
