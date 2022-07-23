package efub.team4.backend_eweather.domain.sky.dto;

import efub.team4.backend_eweather.domain.dayNight.dto.DayNightMapper;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkyMapper {
    private final SkyRepository skyRepository;
    private final DayNightRepository dayNightRepository;
    private final DayNightMapper dayNightMapper;

    public Sky createRequestDtoToEntity(SkyDto.SkyCreateDto requestDto) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(requestDto.getTime())
                .orElseThrow(() -> new DayNightNotFoundException("DayNight Not Found with time = " + requestDto.getTime()));

        return Sky.builder()
                .skyName(requestDto.getSkyName())
                .skyCode(requestDto.getSkyCode())
                .dayNight(dayNight)
                .skyBackGroundFileUrl(requestDto.getSkyBackGroundFileUrl())
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

    public SkyDto.SkyResponseDtoWithUrl getUrlFromEntity(Sky entity){
        return SkyDto.SkyResponseDtoWithUrl.builder()
                .id(entity.getId())
                .skyName(entity.getSkyName())
                .skyBackGroundFileUrl(entity.getSkyBackGroundFileUrl())
                .build();
    }
}
