package efub.team4.backend_eweather.domain.vote.dto;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import lombok.*;

import java.util.UUID;

public class VotesDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VotesCreateRequest {
        private UUID votePostsId;
        private boolean isApproved;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VotesResponse {
        private UUID votesId;
        private UserResponseDto userResponseDto;
        private VoteResponseDto voteResponseDto;
        private boolean isApproved;
    }
}
