package efub.team4.backend_eweather.domain.vote.controller;

import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteUpdateRequestDto;
import efub.team4.backend_eweather.domain.vote.service.VotePostsService;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Api(tags = {"투표 API"})
@RequestMapping("/api/v1/votes")
public class VotePostsController {

    @Autowired
    private final VotePostsService votePostsService;

    @PostMapping
    @ApiOperation(value = "투표 게시글 등록", notes = "투표 게시글을 업로드한다.")
    public VoteResponseDto savePost(@LoginUser SessionUser user, @RequestBody VoteRequestDto voteRequestDto){
        return votePostsService.savePost(user, voteRequestDto);
    }

    // 좋아요, 싫어요 기능
    @PutMapping("/good/{id}")
    @ApiOperation(value = "투표 게시글 좋아요 수정", notes = "투표 게시글의 좋아요 수를 수정한다.")
    public VoteResponseDto votesGood(@PathVariable UUID id, @LoginUser SessionUser sessionUser){
        return votePostsService.updateGood(id, sessionUser);
    }

    @PutMapping("/bad/{id}")
    @ApiOperation(value = "투표 게시글 싫어요 수정", notes = "투표 게시글의 싫어요 수를 수정한다.")
    public VoteResponseDto voteBad(@PathVariable UUID id, @LoginUser SessionUser sessionUser){
        return votePostsService.updateBad(id, sessionUser);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "투표 게시글 옷 수정", notes = "투표 게시글에 작성된 옷을 수정한다.")
    public UUID update(@PathVariable UUID id, @RequestBody VoteUpdateRequestDto requestDto){
        return votePostsService.update(id, requestDto);
    }

    // 투표 게시글 전체 조회 기능
    @GetMapping()
    @ApiOperation(value = "투표 게시글 전체 조회", notes = "전체 투표 게시글을 조회한다.")
    public List<VoteResponseDto> findAllVotePostsList(){
        return votePostsService.findAllVotePostsList();
    }

    // 투표 게시글 상세 조회 기능 - UUID의 경우 findBy함수를 사용할 수 없어 방안 마련해야 함
    @GetMapping("/{id}")
    @ApiOperation(value = "투표 게시글 상세 조회", notes = "하나의 유저가 작성한 투표 게시글을 조회한다.")
    public VoteResponseDto findById(@PathVariable UUID id) {
        return votePostsService.findById(id);
    }


    // 삭제 기능
    @DeleteMapping("/{id}")
    @ApiOperation(value = "투표 게시글 삭제", notes = "투표 게시글을 삭제한다.")
    public UUID deleteVotePost(@PathVariable UUID id){
        votePostsService.deleteVotePost(id);
        return id;
    }
}