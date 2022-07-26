package efub.team4.backend_eweather.domain.sky.dto;

import efub.team4.backend_eweather.domain.dayNight.dto.DayNightDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

public class SkyDto {
    @Getter
    @Setter
    public static class SkyCreateDto{
        @ApiModelProperty(value = "SKY 명", example = "", required = true)
        private String skyName;

        @ApiModelProperty(value = "SKY CODE", example = "1", required = true)
        private Integer skyCode;

        @ApiModelProperty(value = "현재 시간", example = "1200", required = true)
        private String time;

        @ApiModelProperty(value = " sky url", example = "https://-.com", required = true)
        private String skyBackGroundFileUrl;
    }

    @Getter
    @Setter
    public static class SkyRequestDto{
        @ApiModelProperty(value = "SKY CODE", example = "1", required = true)
        private String skyCode;

        @ApiModelProperty(value = "현재 시간", example = "1200", required = true)
        private String time;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkyResponseDto{
        @ApiModelProperty(value = "SKY ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "SKY 명", example = "", required = true)
        private String skyName;

        @ApiModelProperty(value = "SKY CODE", example = "1", required = true)
        private Integer skyCode;

        @ApiModelProperty(value = "DAYNIGHT Response DTO", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private DayNightDto.DayNightResponseDto dayNightResponseDto;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkyResponseDtoWithUrl{
        @ApiModelProperty(value = "SKY ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "SKY 명", example = "", required = true)
        private String skyName;

        @ApiModelProperty(value = "SKY CODE", example = "1", required = true)
        private Integer skyCode;

        @ApiModelProperty(value = " sky url", example = "https://-.com", required = true)
        private String skyBackGroundFileUrl;
    }
}
