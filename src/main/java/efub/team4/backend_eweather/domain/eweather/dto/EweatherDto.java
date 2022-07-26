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
        @ApiModelProperty(value = "예보 발표 날짜", example = "20220724", required = true)
        private String baseDate;
        @ApiModelProperty(value = "예보 발표 시간", example = "1300", required = true)
        private String baseTime;
        @ApiModelProperty(value = "예측 날짜", example = "20220724", required = true)
        private String fcstDate;
        @ApiModelProperty(value = "예측 시간", example = "1300", required = true)
        private String fcstTime;
        @ApiModelProperty(value = "POP 확률", example = "10.0", required = true)
        private String pop;
        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private String pty;
        @ApiModelProperty(value = "SKY CODE", example = "3", required = true)
        private String sky;
        @ApiModelProperty(value = "최저 기온", example = "28.0", required = true)
        private String tmn;
        @ApiModelProperty(value = "현재 기온", example = "30.0", required = true)
        private String tmp;
        @ApiModelProperty(value = "최고 기온", example = "32.0", required = true)
        private String tmx;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrentResponseDto{
        @ApiModelProperty(value = "예측 날짜", example = "20220724", required = true)
        private String forecastDate;
        @ApiModelProperty(value = "예측 시간", example = "1300", required = true)
        private String forecastTime;
        @ApiModelProperty(value = "최저 기온", example = "28", required = true)
        private Integer minTemperature;
        @ApiModelProperty(value = "현재 기온", example = "30", required = true)
        private Integer currentTemperature;
        @ApiModelProperty(value = "최대 기온", example = "32", required = true)
        private Integer maxTemperature;
        @ApiModelProperty(value = "강수 확률", example = "10", required = true)
        private Integer rainfallPercentage;
        @ApiModelProperty(value = "PTY RESPONSE DTO", required = true)
        private PtyDto.PtyResponseDtoWithUrl ptyResponseDto;
        @ApiModelProperty(value = "SKY RESPONSE DTO", required = true)
        private SkyDto.SkyResponseDtoWithUrl skyResponseDto;
        @ApiModelProperty(value = "ICON RESPONSE DTO", required = true)
        private IconDto.IconResponseUrlDto iconResponseDto;
        @ApiModelProperty(value = "BEAR RESPONSE DTO", required = true)
        private BearDto.BearResponseDto bearResponseDto;
        @ApiModelProperty(value = "SEASON RESPONSE DTO", required = true)
        private SeasonDto.SeasonResponseDto seasonResponseDto;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ForecastWeatherResponseDto{
        @ApiModelProperty(value = "예측 시간", example = "1300", required = true)
        private String forecastTime;
        @ApiModelProperty(value = "현재 기온", example = "30", required = true)
        private Integer temperature;
        @ApiModelProperty(value = "ICON RESPONSE DTO", required = true)
        private IconDto.IconResponseUrlDto iconResponseDto;
    }
}
