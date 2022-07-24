package efub.team4.backend_eweather.domain.season.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class SeasonDto {
    @Getter
    @Setter
    public static class SeasonCreateDto {
        @NotEmpty
        private Integer startMonth;

        @NotEmpty
        private  Integer endMonth;

        @NotEmpty
        private String seasonName;

        @NotEmpty
        private String seasonBackGroundFileUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeasonResponseDto {
        private UUID id;
        private String seasonName;
        private String seasonBackGroundFileUrl;
    }
}
