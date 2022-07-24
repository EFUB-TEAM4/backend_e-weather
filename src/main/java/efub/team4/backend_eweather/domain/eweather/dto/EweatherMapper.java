package efub.team4.backend_eweather.domain.eweather.dto;

import efub.team4.backend_eweather.domain.bear.dto.BearMapper;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.eweather.entity.Eweather;
import efub.team4.backend_eweather.domain.icon.dto.IconMapper;
import efub.team4.backend_eweather.domain.pty.dto.PtyMapper;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.season.dto.SeasonMapper;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyMapper;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EweatherMapper {

    private final SkyMapper skyMapper;
    private final PtyMapper ptyMapper;
    private final IconMapper iconMapper;
    private final BearMapper bearMapper;
    private final SeasonMapper seasonMapper;

    public EweatherDto.CurrentResponseDto fromEntity(Eweather eweather) {
        return EweatherDto.CurrentResponseDto.builder()
                .forecastDate(eweather.getForecastDate())
                .forecastTime(eweather.getForecastTime())
                .currentTemperature(eweather.getCurrentTemperature())
                .minTemperature(eweather.getMinTemperature())
                .maxTemperature(eweather.getMaxTemperature())
                .rainfallPercentage(eweather.getRainfallPercentage())
                .skyResponseDto(skyMapper.fromEntityWithUrl(eweather.getSky()))
                .ptyResponseDto(ptyMapper.fromEntityWithUrl(eweather.getPty()))
                .iconResponseDto(iconMapper.iconResponseUrlDto(eweather.getIcon()))
                .bearResponseDto(bearMapper.bearResponseDto(eweather.getBear()))
                .seasonResponseDto(seasonMapper.fromEntity(eweather.getSeason()))
                .build();
    }
}
