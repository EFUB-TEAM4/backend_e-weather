package efub.team4.backend_eweather.domain.media.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UploadedFileNotFoundException extends ResourceNotFoundException {
    public UploadedFileNotFoundException(String message) {
        super(message);
    }
}
