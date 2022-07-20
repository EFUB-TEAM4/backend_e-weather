package efub.team4.backend_eweather.domain.vote.service;

import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.repository.VotePostsRespsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VotePostsService {

    private final VotePostsRespsitory votePostsRespsitory;
    @Transactional
    public VoteResponseDto savePost(VoteRequestDto voteRequestDto) {
        // voteRequestDto entity

    }
}
