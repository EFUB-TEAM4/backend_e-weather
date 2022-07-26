package efub.team4.backend_eweather.domain.vote.dto;

import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class VoteResponseDto {

    private UUID id;
    private UUID userId;
    private String building;
    private String clothes;
    private Long good;
    private Long bad;

    @Builder
    public VoteResponseDto(VotePosts entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.building = entity.getBuilding();
        this.clothes = entity.getClothes();
        this.good = entity.getGood();
        this.bad = entity.getBad();
    }
}