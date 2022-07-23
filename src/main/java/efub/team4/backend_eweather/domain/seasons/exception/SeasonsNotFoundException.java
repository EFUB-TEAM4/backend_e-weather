package efub.team4.backend_eweather.domain.seasons.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SeasonsNotFoundException extends ResourceNotFoundException {
    public SeasonsNotFoundException(String message){
        super(message);
    }
}
