package efub.team4.backend_eweather.domain.media.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UploadedFileInvalidException extends DataIntegrityViolationException {
    public UploadedFileInvalidException(String message) {
        super(message);
    }
}
