package efub.team4.backend_eweather.domain.vote.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class VoteAlreadyExistsException extends DataIntegrityViolationException {
    public VoteAlreadyExistsException(String message) {
        super(message);
    }
}
