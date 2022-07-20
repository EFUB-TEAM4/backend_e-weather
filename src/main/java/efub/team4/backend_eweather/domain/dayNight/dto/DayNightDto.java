package efub.team4.backend_eweather.domain.dayNight.dto;

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
        private LocalTime time;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DayNightResponseDto {
        private UUID id;
        private String timeName;
        private LocalTime time;
    }

}
