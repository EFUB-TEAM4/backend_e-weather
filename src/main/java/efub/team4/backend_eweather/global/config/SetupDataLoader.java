package efub.team4.backend_eweather.global.config;

import com.amazonaws.services.s3.AmazonS3;
import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.repository.BearRepository;
import efub.team4.backend_eweather.domain.bear.service.BearService;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarDto;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarMapper;
import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.repository.CalendarRepository;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.dayNight.service.DayNightService;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.icon.service.IconService;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.pty.service.PtyService;
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.season.repository.SeasonRepository;
import efub.team4.backend_eweather.domain.season.service.SeasonService;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.sky.service.SkyService;
import efub.team4.backend_eweather.domain.temperature.service.TemperatureService;
import efub.team4.backend_eweather.domain.weather.dto.CalendarWeatherResponseDto;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private final OpenWeatherAPI openWeatherAPI = new OpenWeatherAPI();

    @Autowired
    private final CalendarMapper calendarMapper;

    @Autowired
    private final SkyService skyService;

    @Autowired
    private final PtyService ptyService;

    @Autowired
    private final IconService iconService;

    @Autowired
    private final DayNightService dayNightService;

    @Autowired
    private final SeasonService seasonService;

    @Autowired
    private final TemperatureService temperatureService;

    @Autowired
    private final BearService bearService;

    private final DayNightRepository dayNightRepository;
    private final SkyRepository skyRepository;
    private final PtyRepository ptyRepository;
    private final IconRepository iconRepository;
    private final TemperatureRepository temperatureRepository;
    private final CalendarRepository calendarRepository;
    private final SeasonRepository seasonRepository;
    private final BearRepository bearRepository;


    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucketName;

    @Transactional
    public void createInitialFields() {
        String url = "https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/background/sky/1day.png";

        ArrayList<Integer> skyCodeContent = new ArrayList<Integer>(Arrays.asList(1, 3, 4));
        ArrayList<String> skyStringContent = new ArrayList<String>(Arrays.asList("맑음", "구름많음", "흐림"));
        ArrayList<Integer> ptyCodeContent = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
        ArrayList<String> ptyStringContent = new ArrayList<String>(Arrays.asList("없음", "비", "비/눈", "눈", "소나기"));
        ArrayList<Integer> minInt = new ArrayList<Integer>(Arrays.asList(-20, 5, 9, 12, 17, 20, 23, 28));
        ArrayList<Integer> maxInt = new ArrayList<Integer>(Arrays.asList(4, 8, 11, 16, 19, 22, 27, 40));
        ArrayList<String> season = new ArrayList<String>(Arrays.asList("봄", "여름", "가을", "겨울"));
        ArrayList<Integer> startMonth = new ArrayList<Integer>(Arrays.asList(3, 6, 9, 2));
        ArrayList<Integer> endMonth = new ArrayList<Integer>(Arrays.asList(5, 8, 11, 12));


        DayNight day = DayNight.builder()
                .timeName("day")
                .startTime(LocalTime.parse("06:00"))
                .endTime(LocalTime.parse("19:00"))
                .build();

        dayNightService.save(day);
        DayNight night = DayNight.builder()
                .timeName("night")
                .startTime(LocalTime.parse("19:00"))
                .endTime(LocalTime.parse("06:00"))
                .build();
        dayNightService.save(night);


        for (int i = 0; i < 3; i++) {
            Sky dayNth = Sky.builder()
                    .skyName("낮_" + skyStringContent.get(i))
                    .dayNight(day)
                    .skyCode(skyCodeContent.get(i))
                    .skyBackGroundFileUrl(url)
                    .build();
            skyService.save(dayNth);

            Sky nightNth = Sky.builder()
                    .skyName("밤_" + skyStringContent.get(i))
                    .dayNight(night)
                    .skyCode(skyCodeContent.get(i))
                    .skyBackGroundFileUrl(url)
                    .build();
            skyService.save(nightNth);

        }


        for (int i = 0; i < 5; i++) {
            Pty ptyNth = Pty.builder()
                    .ptyName(ptyStringContent.get(i))
                    .ptyCode(ptyCodeContent.get(i))
                    .ptyBackGroundFileUrl(url)
                    .build();
            ptyService.save(ptyNth);
        }

        for (int i = 0; i < 8; i++) {
            Temperature tempNth = Temperature.builder()
                    .minTemperature(minInt.get(i))
                    .maxTemperature(maxInt.get(i))
                    .build();
            temperatureService.save(tempNth);
        }


        for (int i = 0; i < 4; i++) {
            Season seasonNth = Season.builder()
                    .startMonth(startMonth.get(i))
                    .endMonth(endMonth.get(i))
                    .seasonName(season.get(i))
                    .seasonsBackGroundFileUrl(url)
                    .build();
            seasonService.save(seasonNth);
        }


        Optional<DayNight> dayNth = dayNightRepository.findDayNightWithQueryByTime("1300");
        System.out.println(dayNth.get().getTimeName());
        for (int i = 0; i < 5; i++) {
            Optional<Pty> ptyNth = ptyRepository.findByPtyCode(ptyCodeContent.get(i));

            for (int j = 0; j < 3; j++) {
                Optional<Sky> skyNth = skyRepository.findSkyBySkyCodeAndDayNight_Id(skyCodeContent.get(j), dayNth.get().getId());
                Icon iconNth = Icon.builder()
                        .iconName(skyNth.get().getSkyName() + "_" + ptyNth.get().getPtyName())
                        .sky(skyNth.get())
                        .pty(ptyNth.get())
                        .iconUrl(url)
                        .build();

                iconService.save(iconNth);
            }

        }

        Optional<DayNight> nightNth = dayNightRepository.findDayNightWithQueryByTime("2000");
        System.out.println(nightNth.get().getTimeName());
        for (int i = 0; i < 5; i++) {
            Optional<Pty> ptyNth = ptyRepository.findByPtyCode(ptyCodeContent.get(i));

            for (int j = 0; j < 3; j++) {
                Optional<Sky> skyNth = skyRepository.findSkyBySkyCodeAndDayNight_Id(skyCodeContent.get(j), nightNth.get().getId());
                Icon iconNth = Icon.builder()
                        .iconName(skyNth.get().getSkyName() + "_" + ptyNth.get().getPtyName())
                        .sky(skyNth.get())
                        .pty(ptyNth.get())
                        .iconUrl(url)
                        .build();

                iconService.save(iconNth);
            }

        }

        ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(0, 6, 10, 14, 18, 21, 24, 29));
        for (int i = 0; i < 5; i++) {
            Optional<Pty> ptyNth = ptyRepository.findByPtyCode(ptyCodeContent.get(i));

            for (int j = 0; j < 8; j++) {
                Optional<Temperature> temperatureNth = temperatureRepository.findByTemperature(temp.get(j));
                Bear bear = Bear.builder()
                        .pty(ptyNth.get())
                        .temperature(temperatureNth.get())
                        .clothName(ptyNth.get().getPtyName() + "_" + temperatureNth.get().getMinTemperature() + "_" + temperatureNth.get().getMaxTemperature())
                        .bearFileUrl(url)
                        .build();

                bearService.save(bear);
            }
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        //createInitialFields();

    }
}