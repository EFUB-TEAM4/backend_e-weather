package efub.team4.backend_eweather.domain.eweather.dto;

import efub.team4.backend_eweather.domain.bear.dto.BearMapper;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.eweather.entity.Eweather;
import efub.team4.backend_eweather.domain.icon.dto.IconMapper;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.exception.IconNotFoundException;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.dto.PtyMapper;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.season.dto.SeasonMapper;

import efub.team4.backend_eweather.domain.sky.dto.SkyMapper;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.weather.dto.ForcastResponseDto;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EweatherMapper {

    private final SkyMapper skyMapper;
    private final PtyMapper ptyMapper;
    private final IconMapper iconMapper;
    private final BearMapper bearMapper;
    private final SeasonMapper seasonMapper;

    private final SkyRepository skyRepository;
    private final DayNightRepository dayNightRepository;
    private final PtyRepository ptyRepository;
    private final IconRepository iconRepository;

    public EweatherDto.ForecastWeatherResponseDto fromForecasts(ForcastResponseDto responseDto) {

        Double temp = Double.parseDouble(responseDto.getTmp());

        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(responseDto.getFcstTime())
                .orElseThrow(() -> new DayNightNotFoundException("DayNight not found exception with time"));
        
        Sky sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(responseDto.getSky()), dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky not found with time and skyCode"));

        Pty pty = ptyRepository.findByPtyCode(Integer.parseInt(responseDto.getPty()))
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode"));

        Icon icon = iconRepository.findIconBySky_IdAndPty_Id(sky.getId(), pty.getId())
                .orElseThrow(() -> new IconNotFoundException("Icon not found with sky and pty"));

        return EweatherDto.ForecastWeatherResponseDto.builder()
                .forecastTime(responseDto.getFcstTime())
                .temperature(temp.intValue())
                .iconResponseDto(iconMapper.iconResponseUrlDto(icon))
                .build();
    }

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
