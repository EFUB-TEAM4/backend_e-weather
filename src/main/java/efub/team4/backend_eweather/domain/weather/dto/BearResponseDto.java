package efub.team4.backend_eweather.domain.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BearResponseDto {
    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;
    private String tmp;
    private String sky;
    private String pty;

    @Builder
    public BearResponseDto(String baseDate, String baseTime, String fcstDate, String fcstTime, String tmp, String sky, String pty) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.tmp = tmp;
        this.sky = sky;
        this.pty = pty;
    }
}
