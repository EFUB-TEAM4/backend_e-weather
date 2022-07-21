package efub.team4.backend_eweather.domain.dayNight.dto;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DayNightMapper {
    private final DayNightRepository dayNightRepository;

    public DayNight createRequestDtoToEntity(DayNightDto.DayNightCreateDto requestDto) {
        return DayNight.builder()
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .build();
    }

    public DayNightDto.DayNightResponseDto fromEntity(DayNight entity) {
        return DayNightDto.DayNightResponseDto.builder()
                .id(entity.getId())
                .timeName(entity.getTimeName())
                .build();
    }


}
