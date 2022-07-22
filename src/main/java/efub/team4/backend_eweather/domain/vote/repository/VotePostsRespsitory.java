package efub.team4.backend_eweather.domain.vote.repository;

import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VotePostsRespsitory extends JpaRepository<VotePosts, Long> {

    // void deleteVotePost(VotePosts votePosts);
}