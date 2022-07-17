package efub.team4.backend_eweather.domain.calendar.dto;

import com.sun.istack.NotNull;
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
        @NotNull
        private String nickname;
        @NotEmpty
        private String description;

        @NotNull
        private Integer temperature;

        @NotNull
        private Integer max_temperature;

        @NotNull
        private Integer min_temperature;

        @Column(precision = 10, scale = 4)
        private BigDecimal pty_probability;
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
        private UserResponseDto userResDto;
        private String description;
        private Integer temperature;
        private Integer max_temperature;
        private Integer min_temperature;
        private LocalDateTime calendarCreatedOn;
    }
}
