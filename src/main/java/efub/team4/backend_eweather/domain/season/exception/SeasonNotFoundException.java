package efub.team4.backend_eweather.domain.season.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SeasonNotFoundException extends ResourceNotFoundException {
    public SeasonNotFoundException(String message){
        super(message);
    }
}
