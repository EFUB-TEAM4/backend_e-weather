package efub.team4.backend_eweather.domain.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CurrentWeatherResponseDto {

    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;
    private String tmp;
    private String tmx;
    private String tmn;
    private String sky;
    private String pop;
    private String pty;

    @Builder
    public CurrentWeatherResponseDto(String baseDate, String baseTime, String fcstDate, String fcstTime, String tmp, String tmx, String tmn, String sky, String pop, String pty) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.tmp = tmp;
        this.tmx = tmx;
        this.tmn = tmn;
        this.sky = sky;
        this.pop = pop;
        this.pty = pty;
    }
}
