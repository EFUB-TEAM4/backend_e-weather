package efub.team4.backend_eweather.domain.eweather.dto;

import efub.team4.backend_eweather.domain.bear.dto.BearDto;
import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.season.dto.SeasonDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

public class EweatherDto {
    @Getter
    @Setter
    public static class CurrentWeatherCreateDto{
        private String baseDate;
        private String baseTime;
        private String fcstDate;
        private String fcstTime;
        private String pop;
        private String pty;
        private String sky;
        private String tmn;
        private String tmp;
        private String tmx;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrentResponseDto{
        private String forecastDate;
        private String forecastTime;
        private Integer minTemperature;
        private Integer currentTemperature;
        private Integer maxTemperature;
        private Integer rainfallPercentage;
        private PtyDto.PtyResponseDtoWithUrl ptyResponseDto;
        private SkyDto.SkyResponseDtoWithUrl skyResponseDto;
        private IconDto.IconResponseUrlDto iconResponseDto;
        private BearDto.BearResponseDto bearResponseDto;
        private SeasonDto.SeasonResponseDto seasonResponseDto;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ForecastWeatherResponseDto{
        private String forecastTime;
        private Integer temperature;
        private IconDto.IconResponseUrlDto iconResponseDto;
    }
}
