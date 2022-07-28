package efub.team4.backend_eweather.domain.vote.repository;

import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VotesRepository extends JpaRepository<Votes, UUID>, JpaSpecificationExecutor<Votes> {
    List<Votes> findAllByVotePosts(VotePosts votePosts);

    Optional<Votes> findVotesByVotePostsAndUser(VotePosts votePosts, User user);
}
