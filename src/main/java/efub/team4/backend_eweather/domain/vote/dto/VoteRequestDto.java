package efub.team4.backend_eweather.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VoteRequestDto {

    private String building;
    private String clothes;

    @Builder
    public VoteRequestDto(String building, String clothes) {
        this.building = building;
        this.clothes = clothes;
    }
}
