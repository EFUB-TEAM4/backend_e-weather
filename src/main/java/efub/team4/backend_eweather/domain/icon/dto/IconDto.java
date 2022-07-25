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
        private String iconName;
        private Integer skyCode;
        private Integer ptyCode;

        @Size(max = 50)
        @NotEmpty
        private String time;
        @NotEmpty
        private String iconUrl;
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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IconResponseUrlDto {
        private UUID id;
        private String iconName;
        private String iconUrl;
    }
}
