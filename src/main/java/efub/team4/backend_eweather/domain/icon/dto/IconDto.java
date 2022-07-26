package efub.team4.backend_eweather.domain.icon.dto;

import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import io.swagger.annotations.ApiModelProperty;
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
        @ApiModelProperty(value = "아이콘 명", example = "낮_0_1", required = true)
        private String iconName;

        @ApiModelProperty(value = "SKY CODE", example = "3", required = true)
        private Integer skyCode;

        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private Integer ptyCode;

        @ApiModelProperty(value = "예측 시간", example = "1300", required = true)
        private String time;

        @ApiModelProperty(value = "icon url", example = "https://-.com", required = true)
        private String iconUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IconResponseDto {
        @ApiModelProperty(value = "ICON ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "SKY Response DTO", required = true)
        private SkyDto.SkyResponseDto skyResponseDto;

        @ApiModelProperty(value = "SKY Response DTO", required = true)
        private PtyDto.PtyResponseDto ptyResponseDto;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IconResponseUrlDto {
        @ApiModelProperty(value = "ICON ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "아이콘 명", example = "낮_0_1", required = true)
        private String iconName;

        @ApiModelProperty(value = "icon url", example = "https://-.com", required = true)
        private String iconUrl;
    }
}
