package efub.team4.backend_eweather.domain.item.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ItemAlreadyExistsException extends DataIntegrityViolationException {

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

}
