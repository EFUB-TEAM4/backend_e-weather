package efub.team4.backend_eweather.domain.icon.dto;

import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.UUID;

public class IconDto {
    @Getter
    @Setter
    public static class IconCreateDto {
        @NotEmpty
        private Integer skyCode;

        @NotEmpty
        private Integer ptyCode;

        @Size(max = 50)
        @NotEmpty
        private String time;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IconResponseDto {
        private UUID id;
        private SkyDto.SkyResponseDto skyResponseDto;
        private PtyDto.PtyResponseDto ptyResponseDto;
    }
}
