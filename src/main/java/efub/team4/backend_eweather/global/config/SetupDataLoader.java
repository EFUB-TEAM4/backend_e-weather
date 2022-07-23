package efub.team4.backend_eweather.global.config;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.dayNight.service.DayNightService;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.icon.service.IconService;
import efub.team4.backend_eweather.domain.item.entity.Item;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.pty.service.PtyService;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.sky.service.SkyService;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.weather.dto.CalendarWeatherResponseDto;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private final OpenWeatherAPI openWeatherAPI = new OpenWeatherAPI();

    private final DayNightRepository dayNightRepository;
    private final SkyRepository skyRepository;
    private final PtyRepository ptyRepository;
    private final IconRepository iconRepository;


    @Transactional
    public void createInitialFields() {
        DayNight day = DayNight.builder()
                .timeName("day")
                .startTime(LocalTime.parse("06:00"))
                .endTime(LocalTime.parse("19:00"))
                .build();
        dayNightRepository.save(day);
        DayNight night = DayNight.builder()
                .timeName("night")
                .startTime(LocalTime.parse("19:00"))
                .endTime(LocalTime.parse("06:00"))
                .build();
        dayNightRepository.save(night);

        for (int i = 0; i < 9; i++) {
            Integer code = i;
            Sky dayNth = Sky.builder()
                    .skyName("낮 날씨 상태" + i)
                    .dayNight(day)
                    .skyCode(code)
                    .build();
            skyRepository.save(dayNth);
        }

        for (int i = 0; i < 9; i++) {
            Integer code = i;
            Sky nightNth = Sky.builder()
                    .skyName("밤 날씨 상태" + i)
                    .dayNight(night)
                    .skyCode(code)
                    .build();
            skyRepository.save(nightNth);
        }

        for (int i = 0; i < 5; i++) {
            Integer code = i;
            Pty ptyNth = Pty.builder()
                    .ptyName("강우강수" + i)
                    .ptyCode(code)
                    .build();
            ptyRepository.save(ptyNth);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createInitialFields();


        Optional<DayNight> dayNight = dayNightRepository.findDayNightWithQueryByTime("1200");
        Optional<Sky> sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt("1"), dayNight.get().getId());
        Optional<Pty> pty = ptyRepository.findByPtyCode(Integer.parseInt("2"));


        Icon icon = Icon.builder()
                .iconName("icon")
                .sky(sky.get())
                .pty(pty.get())
                .build();

        iconRepository.save(icon);

        /*
        try {
            CalendarWeatherResponseDto responseDto = openWeatherAPI.findCalendarWeather();
            String skyCode = responseDto.getSky();
            String ptyCode = responseDto.getPty();
            String time = responseDto.getFcstTime();

            System.out.println(skyCode);
            System.out.println(ptyCode);
            System.out.println(time);


            Optional<DayNight> dayNight = dayNightRepository.findDayNightWithQueryByTime(time);
            System.out.println(dayNight.get().getTimeName());
            Optional<Sky> sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(skyCode), dayNight.get().getId());
            Optional<Pty> pty = ptyRepository.findByPtyCode(Integer.parseInt(ptyCode));


            Icon icon = Icon.builder()
                    .iconName("icon")
                    .sky(sky.get())
                    .pty(pty.get())
                    .build();

            iconRepository.save(icon);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
    }
}
