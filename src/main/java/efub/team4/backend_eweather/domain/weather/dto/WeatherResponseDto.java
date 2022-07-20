package efub.team4.backend_eweather.domain.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WeatherResponseDto {

    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;
    private String category;
    private String value;

    @Builder
    public WeatherResponseDto(String baseDate, String baseTime, String fcstDate, String fcstTime, String category, String value) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.category = category;
        this.value = value;
    }
}
