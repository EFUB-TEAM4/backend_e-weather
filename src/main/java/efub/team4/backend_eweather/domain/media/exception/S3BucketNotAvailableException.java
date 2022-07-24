package efub.team4.backend_eweather.domain.media.exception;

public class S3BucketNotAvailableException extends IllegalStateException {
    public S3BucketNotAvailableException(String message) {
        super(message);
    }
}
