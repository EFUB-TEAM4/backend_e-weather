package efub.team4.backend_eweather.domain.dayNight.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.UUID;

public class DayNightDto {
    @Getter
    @Setter
    public static class DayNightCreateDto {
        @Size(max = 50)
        @NotEmpty
        private String timeName;
        @Size(max = 50)
        @NotEmpty
        private LocalTime startTime;
        @Size(max = 50)
        @NotEmpty
        private LocalTime endTime;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DayNightResponseDto {
        @ApiModelProperty(value = "DayNight ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;
        @ApiModelProperty(value = "시간대 명", example = "day", required = true)
        private String timeName;
    }

}
