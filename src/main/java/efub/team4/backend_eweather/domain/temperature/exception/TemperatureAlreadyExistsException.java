package efub.team4.backend_eweather.domain.temperature.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class TemperatureAlreadyExistsException extends DataIntegrityViolationException {
    public TemperatureAlreadyExistsException(String message) {
        super(message);
    }
}
