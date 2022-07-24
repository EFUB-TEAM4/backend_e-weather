package efub.team4.backend_eweather.domain.calendar.dto;

import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.repository.CalendarRepository;
import efub.team4.backend_eweather.domain.icon.dto.IconMapper;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.exception.IconNotFoundException;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.dto.PtyMapper;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.dto.SkyMapper;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CalendarMapper {

    private final IconRepository iconRepository;
    private final SkyRepository skyRepository;
    private final PtyRepository ptyRepository;
    private final UserRepository userRepository;

    private final SkyMapper skyMapper;
    private final PtyMapper ptyMapper;
    private final IconMapper iconMapper;

    public Calendar createRequestDtoToEntity(UUID userId, CalendarDto.CreateRequest requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id=" + userId));

        Icon icon = iconRepository.findById(requestDto.getIconId())
                .orElseThrow(() -> new IconNotFoundException("Icon not found with id=" + requestDto.getIconId()));

        Sky sky = skyRepository.findById(icon.getSky().getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky not found with id = " + icon.getSky().getId()));

        Pty pty = ptyRepository.findById(icon.getPty().getId())
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with id = " + icon.getPty().getId()));

        return Calendar.builder()
                .user(user)
                .icon(icon)
                .sky(sky)
                .pty(pty)
                .description(requestDto.getDescription())
                .currentTemperature(requestDto.getCurrentTemperature())
                .minTemperature(requestDto.getMinTemperature())
                .maxTemperature(requestDto.getMaxTemperature())
                .rainfallPercentage(requestDto.getRainfallPercentage())
                .forecastDate(requestDto.getForecastDate())
                .build();
    }

    public CalendarDto.Response CalendarResponse(Calendar calendar) {
        return CalendarDto.Response.builder()
                .id(calendar.getId())
                .description(calendar.getDescription())
                .currentTemperature(calendar.getCurrentTemperature())
                .minTemperature(calendar.getMinTemperature())
                .maxTemperature(calendar.getMaxTemperature())
                .iconResponseUrlDto(iconMapper.iconResponseUrlDto(calendar.getIcon()))
                .skyResponseDtoWithUrl(skyMapper.fromEntityWithUrl(calendar.getSky()))
                .ptyResponseDtoWithUrl(ptyMapper.fromEntityWithUrl(calendar.getPty()))
                .build();
    }

}
