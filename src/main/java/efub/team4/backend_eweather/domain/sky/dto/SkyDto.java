package efub.team4.backend_eweather.domain.sky.dto;

import efub.team4.backend_eweather.domain.icon.dayNight.dto.DayNightDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

        @Size(max = 50)
        @NotEmpty
        private String time;

        @NotEmpty
        private String skyBackGroundFileUrl;
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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkyResponseDtoWithUrl{
        private UUID id;
        private String skyName;
        private String skyBackGroundFileUrl;
    }
}
