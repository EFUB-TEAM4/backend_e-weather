package efub.team4.backend_eweather.domain.pty.dto;

import io.swagger.annotations.ApiModelProperty;
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
        @ApiModelProperty(value = "PTY 명", example = "비", required = true)
        private String ptyName;

        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private Integer ptyCode;

        @ApiModelProperty(value = "pty url", example = "https://-.com", required = true)
        private String ptyBackGroundFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PtyResponseDto {
        @ApiModelProperty(value = "Pty ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "PTY 명", example = "비", required = true)
        private String ptyName;

        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private Integer ptyCode;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PtyResponseDtoWithUrl {
        @ApiModelProperty(value = "Pty ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "PTY 명", example = "비", required = true)
        private String ptyName;

        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private Integer ptyCode;

        @ApiModelProperty(value = "pty url", example = "https://-.com", required = true)
        private String ptyBackGroundFileUrl;
    }
}
