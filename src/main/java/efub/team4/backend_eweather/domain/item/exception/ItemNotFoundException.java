package efub.team4.backend_eweather.domain.item.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class ItemNotFoundException extends ResourceNotFoundException {

    public ItemNotFoundException(String message) {
        super(message);
    }

}
