package efub.team4.backend_eweather.domain.pty.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.UUID;

public class PtyDto {
    @Getter
    @Setter
    public static class PtyCreateDto {
        @Size(max = 50)
        @NotEmpty
        private String ptyName;
        private Integer ptyCode;
        @NotEmpty
        private String ptyBackGroundFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PtyResponseDto {
        private UUID id;
        private String ptyName;
        private Integer ptyCode;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PtyResponseDtoWithUrl {
        private UUID id;
        private String ptyName;
        private Integer ptyCode;
        private String ptyBackGroundFileUrl;
    }
}
