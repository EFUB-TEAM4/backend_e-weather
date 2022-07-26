package efub.team4.backend_eweather.domain.vote.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteSearchCriteria {
    private UUID userId;
    private UUID votePostsId;
    private Boolean isApproved;
}
