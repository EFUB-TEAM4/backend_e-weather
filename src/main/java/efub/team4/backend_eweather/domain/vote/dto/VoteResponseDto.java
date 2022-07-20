package efub.team4.backend_eweather.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class VoteResponseDto {

    private UUID id;
    private UUID userId;
    private String building;
    private String clothes;

    @Builder
    public VoteResponseDto(UUID id, UUID userId, String building, String clothes) {
        this.id = id;
        this.userId = userId;
        this.building = building;
        this.clothes = clothes;
    }
}
