package efub.team4.backend_eweather.domain.temperature.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class TemperatureDto {
    @Getter
    @Setter
    public static class TemperatureCreateDto {
        @NotEmpty
        private Integer minTemperature;

        @NotEmpty
        private Integer maxTemperature;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemperatureResponseDto {
        private UUID id;
    }
}
