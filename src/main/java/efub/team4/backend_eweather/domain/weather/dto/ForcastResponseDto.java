package efub.team4.backend_eweather.domain.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ForcastResponseDto {

    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;
    private String sky;
    private String tmp;

    @Builder
    public ForcastResponseDto(String baseDate, String baseTime, String fcstDate, String fcstTime, String sky, String tmp) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.sky = sky;
        this.tmp = tmp;
    }
}
