package efub.team4.backend_eweather.domain.temperature.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class TemperatureDto {
    @Getter
    @Setter
    public static class TemperatureCreateDto {
        @ApiModelProperty(value = "최저 기온", example = "30", required = true)
        private Integer minTemperature;

        @ApiModelProperty(value = "최 기온", example = "30", required = true)
        private Integer maxTemperature;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemperatureResponseDto {
        @ApiModelProperty(value = "Temperature ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;
    }
}
