package efub.team4.backend_eweather.domain.sky.dto;

import efub.team4.backend_eweather.domain.dayNight.dto.DayNightDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.UUID;

public class SkyDto {
    @Getter
    @Setter
    public static class SkyCreateDto{
        @Size(max = 50)
        @NotEmpty
        private String skyName;

        @NotEmpty
        private Integer skyCode;

        @DateTimeFormat(pattern = "HH:mm:ss")
        @NotEmpty
        private LocalTime time;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkyResponseDto{
        private UUID id;
        private String skyName;
        private Integer skyCode;
        private DayNightDto.DayNightResponseDto dayNightResponseDto;
    }
}
