package efub.team4.backend_eweather.global.config;

import com.amazonaws.services.s3.AmazonS3;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import efub.team4.backend_eweather.domain.weather.dto.CalendarWeatherResponseDto;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import efub.team4.backend_eweather.domain.temperature.repository.TemperatureRepository;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final TemperatureRepository temperatureRepository;

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucketName;

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
                    .skyBackGroundFileUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                    .build();
            skyRepository.save(dayNth);
        }

        for (int i = 0; i < 9; i++) {
            Integer code = i;
            Sky nightNth = Sky.builder()
                    .skyName("밤 날씨 상태" + i)
                    .dayNight(night)
                    .skyCode(code)
                    .skyBackGroundFileUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                    .build();
            skyRepository.save(nightNth);
        }

        for (int i = 0; i < 5; i++) {
            Integer code = i;
            Pty ptyNth = Pty.builder()
                    .ptyName("강우강수" + i)
                    .ptyCode(code)
                    .ptyBackGroundFileUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                    .build();
            ptyRepository.save(ptyNth);
        }

        int temp = 0;
        while (temp<30){
            Integer minTemp = temp;
            Integer maxTemp = temp + 3;
            Temperature tempNth = Temperature.builder()
                    .minTemperature(minTemp)
                    .maxTemperature(maxTemp)
                    .build();
            temperatureRepository.save(tempNth);
            temp += 4;
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
                .iconUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                .build();

        iconRepository.save(icon);

        boolean isBucket = s3Client.doesBucketExistV2(bucketName);
        System.out.println("bucket exists " + isBucket);
        boolean isObject = s3Client.doesObjectExist(bucketName, "share/bear/bear_01.png");
        System.out.println("object exists " + isObject);

        CurrentWeatherResponseDto responseDto = null;
        try {
            responseDto = openWeatherAPI.findCurrentWeather();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String skyCode = responseDto.getSky();
        String ptyCode = responseDto.getPty();
        String time = responseDto.getFcstTime();

        System.out.println(skyCode);
        System.out.println(ptyCode);
        System.out.println(time);


        Optional<DayNight> dayTempNight = dayNightRepository.findDayNightWithQueryByTime(time);
        System.out.println(dayTempNight.get().getTimeName());
        Optional<Sky> skyTemp = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(skyCode), dayTempNight.get().getId());
        Optional<Pty> ptyTemp = ptyRepository.findByPtyCode(Integer.parseInt(ptyCode));


        Icon iconTemp = Icon.builder()
                .iconName("icon2")
                .sky(skyTemp.get())
                .pty(ptyTemp.get())
                .iconUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                .build();

        iconRepository.save(iconTemp);
    }
}
