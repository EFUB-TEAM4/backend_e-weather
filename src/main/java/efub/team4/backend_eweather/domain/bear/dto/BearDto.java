package efub.team4.backend_eweather.domain.bear.dto;

import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.temperature.dto.TemperatureDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class BearDto {
    @Getter
    @Setter
    public static class BearCreateDto {
        @NotEmpty
        private Integer temperature;

        @NotEmpty
        private Integer ptyCode;

        @NotEmpty
        private String bearFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BearResponseDto {
        private UUID id;
        private PtyDto.PtyResponseDto ptyResponseDto;
        private TemperatureDto.TemperatureResponseDto temperatureResponseDto;
        private String bearFileUrl;
    }

}
