package efub.team4.backend_eweather.global.config;

import com.amazonaws.services.s3.AmazonS3;
import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.repository.BearRepository;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarDto;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarMapper;
import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.repository.CalendarRepository;
import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.season.repository.SeasonRepository;
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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
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
        while (temp < 30) {
            Integer minTemp = temp;
            Integer maxTemp = temp + 3;
            Temperature tempNth = Temperature.builder()
                    .minTemperature(minTemp)
                    .maxTemperature(maxTemp)
                    .build();
            temperatureRepository.save(tempNth);
            temp += 4;
        }

        int month = 1;
        while (month < 13) {
            Integer startMonth = month;
            Integer endMonth = month + 2;
            Season seasonNth = Season.builder()
                    .startMonth(startMonth)
                    .endMonth(endMonth)
                    .seasonName("계절" + month)
                    .seasonsBackGroundFileUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                    .build();
            seasonRepository.save(seasonNth);
            month += 3;
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
 /*
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

        Double cTemp = Double.parseDouble(responseDto.getTmp());
        Double minTemp = Double.parseDouble(responseDto.getTmn());
        Double maxTemp = Double.parseDouble(responseDto.getTmx());
        Double rainPop = Double.parseDouble(responseDto.getPop());

        Date date = new Date();

        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(responseDto.getFcstDate());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        Integer month = Integer.parseInt(new SimpleDateFormat("MM").format(date));

        Optional<DayNight> dayTempNight = dayNightRepository.findDayNightWithQueryByTime(time);
        System.out.println(dayTempNight.get().getTimeName());
        Optional<Sky> skyTemp = skyRepository.findSkyBySkyCodeAndDayNight_Id(Integer.parseInt(skyCode), dayTempNight.get().getId());
        Optional<Pty> ptyTemp = ptyRepository.findByPtyCode(Integer.parseInt(ptyCode));
        Optional<Temperature> tempTemp = temperatureRepository.findByTemperature(cTemp.intValue());

        Icon iconTemp = Icon.builder()
                .iconName("icon2")
                .sky(skyTemp.get())
                .pty(ptyTemp.get())
                .iconUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                .build();

        iconRepository.save(iconTemp);

        Bear bearSave = Bear.builder()
                .pty(ptyTemp.get())
                .temperature(tempTemp.get())
                .clothName("곰돌이옷차림")
                .bearFileUrl("https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/bear/bear_01.png")
                .build();

        bearRepository.save(bearSave);

        Optional<Bear> bear = bearRepository.findBearByPtyAndTemperature(ptyTemp.get(), tempTemp.get());
        Optional<Season> season = seasonRepository.findByMonth(month);


        CalendarDto.CreateRequest createRequest = CalendarDto.CreateRequest.builder()
                .currentTemperature(cTemp.intValue())
                .maxTemperature(maxTemp.intValue())
                .minTemperature(minTemp.intValue())
                .rainfallPercentage(rainPop.intValue())
                .forecastDate(responseDto.getFcstDate())
                .iconId(iconTemp.getId())
                .skyId(skyTemp.get().getId())
                .ptyId(ptyTemp.get().getId())
                .bearId(bear.get().getId())
                .seasonId(season.get().getId())
                .description("게시글 생성")
                .build();
        Calendar calendar = calendarMapper.createRequestDtoToEntity(createRequest);
        calendarRepository.save(calendar);

        CalendarDto.CreateRequest createRequest2 = CalendarDto.CreateRequest.builder()
                .currentTemperature(cTemp.intValue())
                .maxTemperature(maxTemp.intValue())
                .minTemperature(minTemp.intValue())
                .rainfallPercentage(rainPop.intValue())
                .forecastDate("20220724")
                .iconId(iconTemp.getId())
                .skyId(skyTemp.get().getId())
                .ptyId(ptyTemp.get().getId())
                .bearId(bear.get().getId())
                .seasonId(season.get().getId())
                .description("게시글 생성2")
                .build();
        Calendar calendar2 = calendarMapper.createRequestDtoToEntity(createRequest2);
        calendarRepository.save(calendar2);
         */
    }
}
