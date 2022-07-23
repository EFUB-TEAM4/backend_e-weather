package efub.team4.backend_eweather.domain.bear.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BearImageResponseDto {
    String tmpUrl;
    String ptyUrl;
    String skyUrl;
    String seasonUrl;

    @Builder
    public BearImageResponseDto(String tmpUrl, String ptyUrl, String skyUrl, String seasonUrl) {
        this.tmpUrl = tmpUrl;
        this.ptyUrl = ptyUrl;
        this.skyUrl = skyUrl;
        this.seasonUrl = seasonUrl;
    }
}
