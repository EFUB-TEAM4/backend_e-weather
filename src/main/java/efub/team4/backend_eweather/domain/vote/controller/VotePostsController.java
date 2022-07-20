package efub.team4.backend_eweather.domain.vote.controller;

import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.service.VotePostsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votes")
public class VotePostsController {

    private VotePostsService votePostsService;

    @PostMapping
    public VoteResponseDto savePost(@RequestBody VoteRequestDto voteRequestDto){
        return votePostsService.savePost(voteRequestDto);
    }

}
