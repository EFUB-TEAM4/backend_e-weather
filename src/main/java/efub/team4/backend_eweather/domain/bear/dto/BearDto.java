package efub.team4.backend_eweather.domain.bear.dto;

import efub.team4.backend_eweather.domain.season.dto.SeasonDto;
import efub.team4.backend_eweather.domain.temperature.dto.TemperatureDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class BearDto {
    @Getter
    @Setter
    public static class BearCreateDto {
        @ApiModelProperty(value = "현재 기온", example = "30", required = true)
        private Integer temperature;

        @ApiModelProperty(value = "PTY CODE", example = "1", required = true)
        private Integer ptyCode;

        @ApiModelProperty(value = "착장 기준", example = "비/눈_5_8", required = true)
        private String clothName;

        @ApiModelProperty(value = "착장 명", example = "후드티/청바지", required = true)
        private String clothDescription;

        @ApiModelProperty(value = "bear url", example = "https://-.com", required = true)
        private String bearFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BearResponseDto {
        @ApiModelProperty(value = "Bear ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "기온 분류 API", required = true)
        private TemperatureDto.TemperatureResponseDto temperatureResponseDto;

        @ApiModelProperty(value = "착장 기준", example = "비/눈_5_8", required = true)
        private String clothName;

        @ApiModelProperty(value = "착장 명", example = "후드티 / 청바지", required = true)
        private String clothDescription;

        @ApiModelProperty(value = "bear url", example = "https://-.com", required = true)
        private String bearFileUrl;
    }

}
