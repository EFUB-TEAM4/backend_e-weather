package efub.team4.backend_eweather.domain.eweather.service;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.exception.BearNotFoundException;
import efub.team4.backend_eweather.domain.bear.repository.BearRepository;
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
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.season.exception.SeasonNotFoundException;
import efub.team4.backend_eweather.domain.season.repository.SeasonRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.exception.TemperatureNotFoundException;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EweatherService {
    private final SkyRepository skyRepository;
    private final DayNightRepository dayNightRepository;
    private final PtyRepository ptyRepository;
    private final IconRepository iconRepository;
    private final TemperatureRepository temperatureRepository;
    private final BearRepository bearRepository;
    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public Eweather create(CurrentWeatherResponseDto responseDto) {

        Double minTemp = Double.parseDouble(responseDto.getTmn());
        Double maxTemp = Double.parseDouble(responseDto.getTmx());
        Double currentTemp = Double.parseDouble(responseDto.getTmp());
        Double rainfallPer = Double.parseDouble(responseDto.getPop());

        Date date = new Date();

        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(responseDto.getFcstDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer month = Integer.parseInt(new SimpleDateFormat("MM").format(date));

        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(responseDto.getFcstTime())
                .orElseThrow(() -> new DayNightNotFoundException("DayNight Not Found with time = " + responseDto.getFcstTime()));

        Sky sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(responseDto.getSky()), dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky Not Found with skyCode and time"));

        Pty pty = ptyRepository.findByPtyCode(Integer.parseInt(responseDto.getPty()))
                .orElseThrow(() -> new PtyNotFoundException("Pty Not Found with ptyCode"));

        Icon icon = iconRepository.findIconBySky_IdAndPty_Id(sky.getId(), pty.getId())
                .orElseThrow(() -> new IconNotFoundException("Icon Not Found with Sky and Pty"));

        Temperature temperature = temperatureRepository.findByTemperature(currentTemp.intValue())
                .orElseThrow(() -> new TemperatureNotFoundException("Temperature Not Found with current temperature"));

        Bear bear = bearRepository.findBearByPtyAndTemperature(pty, temperature)
                .orElseThrow(() -> new BearNotFoundException("Bear Not Found With pty and temperature"));

        Season season = seasonRepository.findByMonth(month)
                .orElseThrow(() -> new SeasonNotFoundException("Season Not Found With Month"));

        return Eweather.builder()
                .forecastDate(responseDto.getFcstDate())
                .forecastTime(responseDto.getFcstTime())
                .minTemperature(minTemp.intValue())
                .maxTemperature(maxTemp.intValue())
                .currentTemperature(currentTemp.intValue())
                .rainfallPercentage(rainfallPer.intValue())
                .sky(sky)
                .pty(pty)
                .icon(icon)
                .bear(bear)
                .season(season)
                .build();
    }
}
