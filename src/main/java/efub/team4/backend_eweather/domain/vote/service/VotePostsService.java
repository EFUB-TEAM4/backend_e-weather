package efub.team4.backend_eweather.domain.vote.service;

import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.entity.UserRepository;
import efub.team4.backend_eweather.domain.user.service.UserService;
import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.repository.VotePostsRespsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VotePostsService {

    @Autowired
    private final VotePostsRespsitory votePostsRespsitory;

    @Autowired
    private UserService userService;

    public VoteResponseDto buildResponseDto(VotePosts entity){
        return new VoteResponseDto(entity);
    }

    @Transactional
    public VoteResponseDto savePost(VoteRequestDto voteRequestDto) {
        // voteRequestDto -> entity
        User user = userService.getSessionUser();

        System.out.println(user.getEmail());
        System.out.println(voteRequestDto.getBuilding());
        System.out.println(voteRequestDto.getClothes());

        VotePosts votePosts = VotePosts.builder()
                .id(UUID.randomUUID())
                .user(user)
                .building(voteRequestDto.getBuilding())
                .clothes(voteRequestDto.getClothes())
                .build();

        votePostsRespsitory.save(votePosts);

        return buildResponseDto(votePosts);

    }
}
