package efub.team4.backend_eweather.domain.eweather.service;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.eweather.entity.Eweather;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.exception.IconNotFoundException;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EweatherService {
    private final SkyRepository skyRepository;
    private final DayNightRepository dayNightRepository;
    private final PtyRepository ptyRepository;
    private final IconRepository iconRepository;

    @Transactional(readOnly = true)
    public Eweather create(CurrentWeatherResponseDto responseDto) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(responseDto.getFcstTime())
                .orElseThrow(() -> new DayNightNotFoundException("DayNight Not Found with time = " + responseDto.getFcstTime()));

        Sky sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(responseDto.getSky()), dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky Not Found with skyCode and time"));

        Pty pty = ptyRepository.findByPtyCode(Integer.parseInt(responseDto.getPty()))
                .orElseThrow(() -> new PtyNotFoundException("Pty Not Found with ptyCode"));

        Icon icon = iconRepository.findBySkyAndPty(sky, pty)
                .orElseThrow(() -> new IconNotFoundException("Icon Not Found with Sky and Pty"));

        return Eweather.builder()
                .forecastDate(responseDto.getFcstDate())
                .forecastTime(responseDto.getFcstTime())
                .minTemperature(Integer.parseInt(responseDto.getTmn()))
                .maxTemperature(Integer.parseInt(responseDto.getTmx()))
                .currentTemperature(Integer.parseInt(responseDto.getTmp()))
                .rainfallPercentage(Integer.parseInt(responseDto.getPop()))
                .sky(sky)
                .pty(pty)
                .icon(icon)
                .build();
    }
}
