package efub.team4.backend_eweather.domain.calendar.dto;

import com.sun.istack.NotNull;
import efub.team4.backend_eweather.domain.bear.dto.BearDto;
import efub.team4.backend_eweather.domain.eweather.dto.EweatherDto;
import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.season.dto.SeasonDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class CalendarDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalendarCreateRequest {
        private String description;
        private String forecastDate;
        private Integer minTemperature;
        private Integer currentTemperature;
        private Integer maxTemperature;
        private Integer rainfallPercentage;
        private UUID iconId;
        private UUID ptyId;
        private UUID skyId;
        private UUID bearId;
        private UUID seasonId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest{
        private String description;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteRequest {
        @NotBlank
        private UUID id;
        private String message;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID id;
        private UserResponseDto userResponseDto;
        private String description;
        private Integer currentTemperature;
        private Integer maxTemperature;
        private Integer minTemperature;
        private IconDto.IconResponseUrlDto iconResponseUrlDto;
        private SkyDto.SkyResponseDtoWithUrl skyResponseDtoWithUrl;
        private PtyDto.PtyResponseDtoWithUrl ptyResponseDtoWithUrl;
        private BearDto.BearResponseDto bearResponseDto;
        private SeasonDto.SeasonResponseDto seasonResponseDto;
        private LocalDateTime calendarCreatedOn;
        private LocalDateTime calendarUpdatedOn;
    }
}
