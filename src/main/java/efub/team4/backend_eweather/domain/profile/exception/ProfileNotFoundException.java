package efub.team4.backend_eweather.domain.profile.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class ProfileNotFoundException extends ResourceNotFoundException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
