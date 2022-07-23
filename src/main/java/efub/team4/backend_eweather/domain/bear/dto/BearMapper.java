package efub.team4.backend_eweather.domain.bear.dto;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.pty.dto.PtyMapper;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.temperature.dto.TemperatureMapper;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.exception.TemperatureNotFoundException;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BearMapper {

    private final PtyRepository ptyRepository;
    private final TemperatureRepository temperatureRepository;

    private final PtyMapper ptyMapper;
    private final TemperatureMapper temperatureMapper;

    public Bear createRequestDtoToEntity(BearDto.BearCreateDto requestDto){
        Temperature temperature = temperatureRepository.findByTemperature(requestDto.getTemperature())
                .orElseThrow(() -> new TemperatureNotFoundException("Temperature not found"));

        Pty pty = ptyRepository.findByPtyCode(requestDto.getPtyCode())
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode = " + requestDto.getPtyCode()));

        return Bear.builder()
                .temperature(temperature)
                .pty(pty)
                .build();
    }

    public BearDto.BearResponseDto bearResponseDto(Bear entity){
        return BearDto.BearResponseDto.builder()
                .id(entity.getId())
                .temperatureResponseDto(temperatureMapper.fromEntity(entity.getTemperature()))
                .ptyResponseDto(ptyMapper.fromEntity(entity.getPty()))
                .build();
    }
}
