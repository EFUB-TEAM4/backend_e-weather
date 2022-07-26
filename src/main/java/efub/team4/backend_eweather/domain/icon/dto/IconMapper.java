package efub.team4.backend_eweather.domain.icon.dto;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.pty.dto.PtyMapper;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.dto.SkyMapper;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IconMapper {

    private final SkyRepository skyRepository;
    private final PtyRepository ptyRepository;
    private final DayNightRepository dayNightRepository;

    private final SkyMapper skyMapper;
    private final PtyMapper ptyMapper;

    public Icon createRequestDtoToEntity(IconDto.IconCreateDto requestDto) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(requestDto.getTime())
                .orElseThrow(() -> new DayNightNotFoundException("DayNight Not Found with time = " + requestDto.getTime()));

        Sky sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(requestDto.getSkyCode(), dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky not found with specified sky"));

        Pty pty = ptyRepository.findByPtyCode(requestDto.getPtyCode())
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode = " + requestDto.getPtyCode()));

        return Icon.builder()
                .iconName(requestDto.getIconName())
                .sky(sky)
                .pty(pty)
                .iconUrl(requestDto.getIconUrl())
                .build();
    }

    public IconDto.IconResponseDto iconResponseDto(Icon entity){
        return IconDto.IconResponseDto.builder()
                .id(entity.getId())
                .skyResponseDto(skyMapper.fromEntity(entity.getSky()))
                .ptyResponseDto(ptyMapper.fromEntity(entity.getPty()))
                .build();
    }

    public IconDto.IconResponseUrlDto iconResponseUrlDto(Icon entity){
        return IconDto.IconResponseUrlDto.builder()
                .id(entity.getId())
                .iconName(entity.getIconName())
                .iconUrl(entity.getIconUrl())
                .build();
    }
}
