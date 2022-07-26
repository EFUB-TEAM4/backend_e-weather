package efub.team4.backend_eweather.domain.vote.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class VoteNotFoundException extends ResourceNotFoundException {
    public VoteNotFoundException(String message) {
        super(message);
    }
}
