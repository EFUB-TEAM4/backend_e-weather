package efub.team4.backend_eweather.domain.season.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class SeasonDto {
    @Getter
    @Setter
    public static class SeasonCreateDto {
        @ApiModelProperty(value = "계절 시작 달", example = "3", required = true)
        private Integer startMonth;

        @ApiModelProperty(value = "계절 마지막 달", example = "5", required = true)
        private  Integer endMonth;

        @ApiModelProperty(value = "계절명", example = "봄", required = true)
        private String seasonName;

        @ApiModelProperty(value = " season url", example = "https://-.com", required = true)
        private String seasonBackGroundFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeasonResponseDto {
        @ApiModelProperty(value = "Season ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "계절명", example = "봄", required = true)
        private String seasonName;

        @ApiModelProperty(value = " season url", example = "https://-.com", required = true)
        private String seasonBackGroundFileUrl;
    }
}
