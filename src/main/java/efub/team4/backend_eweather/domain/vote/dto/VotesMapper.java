package efub.team4.backend_eweather.domain.vote.dto;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import efub.team4.backend_eweather.domain.vote.repository.VotePostsRespsitory;
import efub.team4.backend_eweather.domain.vote.repository.VotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VotesMapper {
    private final UserRepository userRepository;
    private final VotesRepository votesRepository;
    private final VotePostsRespsitory votePostsRespsitory;

    public Votes createVotesFromRequestDto(UUID userId, VotesDto.VotesCreateRequest requestDto) {
        if (userId.equals("") || userId == null) {
            throw new ResourceNotFoundException("User is null with user ID");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id"));

        VotePosts votePosts = votePostsRespsitory.findById(requestDto.getVotePostsId())
                .orElseThrow(() -> new ResourceNotFoundException("VotePosts not found with id"));

        return Votes.builder()
                .user(user)
                .votePosts(votePosts)
                .isApproved(requestDto.isApproved())
                .build();
    }

    public VotesDto.VotesResponse fromVotesEntity(Votes votes) {
        return VotesDto.VotesResponse.builder()
                .votesId(votes.getId())
                .userResponseDto(new UserResponseDto(votes.getUser()))
                .voteResponseDto(new VoteResponseDto(votes.getVotePosts()))
                .isApproved(votes.isApproved())
                .build();
    }
}
