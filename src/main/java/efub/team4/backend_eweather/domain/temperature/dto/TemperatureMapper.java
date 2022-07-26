package efub.team4.backend_eweather.domain.temperature.dto;

import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemperatureMapper {
    private final TemperatureRepository temperatureRepository;

    public Temperature createRequestDtoToEntity(TemperatureDto.TemperatureCreateDto requestDto){
        return Temperature.builder()
                .minTemperature(requestDto.getMinTemperature())
                .maxTemperature(requestDto.getMaxTemperature())
                .build();
    }

    public TemperatureDto.TemperatureResponseDto fromEntity(Temperature entity) {
        return TemperatureDto.TemperatureResponseDto.builder()
                .id(entity.getId())
                .build();
    }
}
