package efub.team4.backend_eweather.domain.bear.service;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.exception.BearNotFoundException;
import efub.team4.backend_eweather.domain.bear.repository.BearRepository;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.exception.TemperatureNotFoundException;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BearService {
    private final BearRepository bearRepository;
    private final PtyRepository ptyRepository;
    private final TemperatureRepository temperatureRepository;

    @Transactional
    public Bear save(Bear bear) {
        bearRepository.findBearByPtyAndTemperature(bear.getPty(), bear.getTemperature())
                .ifPresent((existedBear) -> {
                    throw new BearNotFoundException("Bear already exists with specified id = " + bear.getId());
                });
        return bearRepository.save(bear);
    }

    @Transactional(readOnly = true)
    public Bear findById(UUID id) {
        return bearRepository.findById(id)
                .orElseThrow(() -> new BearNotFoundException("Bear not found with id"));
    }

    @Transactional(readOnly = true)
    public Bear findByPtyAndTemperature(Integer ptyCode, Integer temperature) {
        Pty pty = ptyRepository.findByPtyCode(ptyCode)
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode"));
        Temperature temp = temperatureRepository.findByTemperature(temperature)
                .orElseThrow(() -> new TemperatureNotFoundException("Temperature not found with specified temperature"));

        return bearRepository.findBearByPtyAndTemperature(pty, temp)
                .orElseThrow(() -> new BearNotFoundException("Bear not found with pty and temperature"));
    }

    @Transactional(readOnly = true)
    public List<Bear> findAll() {
        return bearRepository.findAll();
    }

}
