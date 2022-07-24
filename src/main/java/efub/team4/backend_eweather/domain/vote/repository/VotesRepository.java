package efub.team4.backend_eweather.domain.vote.repository;

import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VotesRepository extends JpaRepository<Votes, UUID> {

    List<Votes> findAllByVotePosts(VotePosts votePosts);
}
