package efub.team4.backend_eweather.domain.temperature.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class TemperatureNotFoundException extends ResourceNotFoundException {
    public TemperatureNotFoundException(String message) { super(message); }
}
