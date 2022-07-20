package efub.team4.backend_eweather.domain.sky.dto;

import efub.team4.backend_eweather.domain.dayNight.dto.DayNightDto;
import efub.team4.backend_eweather.domain.dayNight.dto.DayNightMapper;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkyMapper {
    private final SkyRepository skyRepository;
    private final DayNightMapper dayNightMapper;

    public Sky createRequestDtoToEntity(SkyDto.SkyCreateDto requestDto) {
        return Sky.builder()
                .skyName(requestDto.getSkyName())
                .skyCode(requestDto.getSkyCode())
                .build();
    }

    public SkyDto.SkyResponseDto fromEntity(Sky entity) {
        return SkyDto.SkyResponseDto.builder()
                .id(entity.getId())
                .skyName(entity.getSkyName())
                .skyCode(entity.getSkyCode())
                .dayNightResponseDto(dayNightMapper.fromEntity(entity.getDayNight()))
                .build();
    }
}
