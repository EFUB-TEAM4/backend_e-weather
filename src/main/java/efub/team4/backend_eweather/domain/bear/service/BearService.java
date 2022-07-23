package efub.team4.backend_eweather.domain.bear.service;

import efub.team4.backend_eweather.domain.bear.dto.BearImageResponseDto;
import efub.team4.backend_eweather.domain.weather.dto.BearResponseDto;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import efub.team4.backend_eweather.global.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BearService {

    @Autowired
    private final OpenWeatherAPI openWeatherAPI;

    private final TimeUtil timeUtil = new TimeUtil();

    public final String BEAR_TMP_PREFIX = "https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/bear/tmp/";
    public final String BEAR_SKY_PREFIX = "https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/bear/sky/";
    public final String BEAR_PTY_PREFIX = "https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/bear/pty/";
    public final String BEAR_SEASON_PREFIX = "https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/bear/season/";

    public BearImageResponseDto findBearImage() throws IOException, ParseException {
        BearResponseDto bearInfo = openWeatherAPI.findBearInfo();
        Integer tmp = Integer.parseInt(bearInfo.getTmp());
        String pty = bearInfo.getPty();
        String sky = bearInfo.getSky();
        String tmpUrl = buildTMPURL(tmp, Integer.parseInt(pty));
        String skyUrl = BEAR_SKY_PREFIX + sky + ".png";
        String ptyUrl = BEAR_PTY_PREFIX + pty + ".png";
        String seasonUrl = buildSeasonUrl();

        return BearImageResponseDto.builder()
                .tmpUrl(tmpUrl)
                .skyUrl(skyUrl)
                .ptyUrl(ptyUrl)
                .seasonUrl(seasonUrl)
                .build();

    }

    private String buildSeasonUrl() {
        Integer month = timeUtil.getMonth();
        String season = switch (month) {
            case 12, 1, 2 -> "winter";
            case 3, 4, 5 -> "spring";
            case 6, 7, 8 -> "summer";
            case 9, 10, 11 -> "autumn";
            default -> "";
        };
        return BEAR_SEASON_PREFIX + season + ".png";
    }

    // 이 부분은 DB로 찾는 게 빨라보이긴 함
    // 일단 급한대로... 그리고 후에 리팩토링
    public String buildTMPURL(Integer tmp, Integer pty){
        String name = "";
        if (tmp >= 28){
            name = "28";
        }
        else if(tmp >= 23){
            name = "23";
        }
        else if(tmp >= 20){
            name = "20";
        }
        else if(tmp >= 17){
            name = "17";
        }
        else if(tmp >= 12){
            name = "12";
        }
        else if(tmp >= 9){
            name = "9";
        }
        else if(tmp >= 5){
            name = "5";
        }
        else{
            name = "0";
        }

        return BEAR_TMP_PREFIX + name + ".png";
    }
}
