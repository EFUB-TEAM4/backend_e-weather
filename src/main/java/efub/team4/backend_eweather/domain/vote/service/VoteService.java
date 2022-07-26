package efub.team4.backend_eweather.domain.vote.service;

import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import efub.team4.backend_eweather.domain.vote.exception.VoteAlreadyExistsException;
import efub.team4.backend_eweather.domain.vote.repository.VotePostsRespsitory;
import efub.team4.backend_eweather.domain.vote.repository.VotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final UserRepository userRepository;
    private final VotePostsRespsitory votePostsRespsitory;
    private final VotesRepository votesRepository;

    @Transactional
    public Votes save(Votes vote) {
        Optional<Votes> votes = votesRepository.findVotesByVotePostsAndUser(vote.getVotePosts(), vote.getUser());

        if (votes.isPresent()) {
            throw new VoteAlreadyExistsException("Vote already exists with user and votePosts");
        }

        return votesRepository.save(vote);
    }

    @Transactional(readOnly = true)
    public List<Votes> findByVotePosts(VotePosts votePosts) {
        return votesRepository.findAllByVotePosts(votePosts);
    }

    @Transactional(readOnly = true)
    public List<Votes> findAllVotes(Specification<Votes> spec) {
        return votesRepository.findAll(spec);
    }

}
