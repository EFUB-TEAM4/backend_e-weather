package efub.team4.backend_eweather.domain.calendar.dto;

import com.sun.istack.NotNull;
import efub.team4.backend_eweather.domain.eweather.dto.EweatherDto;
import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
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
    public static class CreateRequest {
        @NotEmpty
        private String description;

        @NotNull
        private String forecastDate;

        @Column(nullable = false)
        private Integer minTemperature;

        @Column(nullable = false)
        private Integer currentTemperature;

        @Column(nullable = false)
        private Integer maxTemperature;

        @Column(nullable = false)
        private Integer rainfallPercentage;

        @NotNull
        private UUID iconId;

        @NotNull
        private UUID ptyId;

        @NotNull
        private UUID skyId;
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
        private LocalDateTime calendarCreatedOn;
        private LocalDateTime calendarUpdatedOn;
    }
}
