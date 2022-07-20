package efub.team4.backend_eweather.domain.vote.controller;

import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteUpdateRequestDto;
import efub.team4.backend_eweather.domain.vote.service.VotePostsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votes")
public class VotePostsController {

    private final VotePostsService votePostsService;

<<<<<<< HEAD


    @PostMapping()
=======
    @PostMapping
>>>>>>> vote
    public VoteResponseDto savePost(@RequestBody VoteRequestDto voteRequestDto){
        return votePostsService.savePost(voteRequestDto);
    }

    // 좋아요, 싫어요 기능
    // @PutMapping


    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody VoteUpdateRequestDto requestDto){
        return votePostsService.update(id, requestDto);
    }

    // 투표 게시글 전체 조회 기능
    @GetMapping("{id}")
    public List<VoteResponseDto> findAllVotePostsList(){
        return votePostsService.findAllVotePostsList();
    }

    // 투표 게시글 상세 조회 기능 - UUID의 경우 findBy함수를 사용할 수 없어 방안 마련해야 함
    @GetMapping("/{id}")
    public VoteResponseDto findById(@PathVariable Long id) {
        return votePostsService.findById(id);
    }


    // 삭제 기능
    @DeleteMapping("/{id}")
    public Long deleteVotePost(@PathVariable Long id){
        votePostsService.deleteVotePost(id);
        return id;
    }
}