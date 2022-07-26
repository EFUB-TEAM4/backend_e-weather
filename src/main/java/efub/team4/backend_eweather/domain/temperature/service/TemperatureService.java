package efub.team4.backend_eweather.domain.temperature.service;

import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.exception.TemperatureAlreadyExistsException;
import efub.team4.backend_eweather.domain.temperature.exception.TemperatureNotFoundException;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    @Transactional
    public Temperature save(Temperature temperature){
        temperatureRepository.findByMinTemperatureAndMaxTemperature(temperature.getMinTemperature(), temperature.getMaxTemperature())
                .ifPresent((existedTemperature)->{
                    throw new TemperatureAlreadyExistsException("Temperature already exists with specified Temperature");
                });
        return temperatureRepository.save(temperature);
    }

    @Transactional(readOnly = true)
    public Temperature findByTemperature(Integer temp) {
        Temperature temperature = temperatureRepository.findByTemperature(temp).orElseThrow(() -> new TemperatureNotFoundException("Temperature not found"));
        return temperature;

    }



}
