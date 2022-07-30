package efub.team4.backend_eweather.domain.vote.controller;

import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.vote.dto.*;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import efub.team4.backend_eweather.domain.vote.service.VotePostsService;
import efub.team4.backend_eweather.domain.vote.service.VoteService;
import efub.team4.backend_eweather.domain.vote.specification.VoteSearchCriteria;
import efub.team4.backend_eweather.domain.vote.specification.VotesSpecification;
import efub.team4.backend_eweather.global.config.auth.CurrentUser;
import efub.team4.backend_eweather.global.dto.ApiResponse;
import efub.team4.backend_eweather.global.outh.entity.RoleType;
import efub.team4.backend_eweather.global.outh.entity.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = {"투표 API"}, description = "투표 생성, 수정, 삭제, 조회")
@RequestMapping("/api/v1/votes")
public class VotePostsController {

    @Autowired
    private final VotePostsService votePostsService;
    private final VoteService voteService;
    private final VotesMapper votesMapper;

    @PostMapping
    @ApiOperation(value = "투표 게시글 등록", notes = "투표 게시글을 업로드한다.")
    public ApiResponse<VoteResponseDto> savePost(@ApiIgnore @CurrentUser UserPrincipal user, @ApiParam(value = "투표 요청 DTO") @RequestBody VoteRequestDto voteRequestDto) throws UserPrincipalNotFoundException {
        if (user == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }
        return ApiResponse.success("vote", votePostsService.savePost(user.getUserId(), voteRequestDto));
    }

    @PostMapping("/yesorno")
    @ApiOperation(value = "착장 찬반 투표", notes = "착장 찬반 투표 진행")
    public ApiResponse<VotesDto.VotesResponse> createVote(@ApiIgnore @CurrentUser UserPrincipal user,
                                                          @ApiParam(value = "찬반 투표 DTO") @RequestBody VotesDto.VotesCreateRequest requestDto) throws UserPrincipalNotFoundException {

        if (user == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }

        Votes vote = voteService.save(votesMapper.createVotesFromRequestDto(user.getUserId(), requestDto));

        return ApiResponse.success("vote", votesMapper.fromVotesEntity(vote));
    }

    @GetMapping("/yesorno")
    @ApiOperation(value = "착장 찬반 투표 조회", notes = "착장 찬반 투표 여부 조건 조회")
    public ApiResponse<List<VotesDto.VotesResponse>> getVotesLists(
            @ApiParam(value = "사용자 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @RequestParam(required = false) UUID userId,
            @ApiParam(value = "투표 게시글 VotePosts ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @RequestParam(required = false) UUID votePostsId,
            @ApiParam(value = "찬반 여부(찬성:true, 반대:false)", example = "true") @RequestParam(required = false) Boolean isApproved
    ) {
        VotesSpecification spec = new VotesSpecification(
                VoteSearchCriteria.builder()
                        .userId(userId)
                        .votePostsId(votePostsId)
                        .isApproved(isApproved)
                        .build());

        List<Votes> response = voteService.findAllVotes(spec);

        return ApiResponse.success("vote", response.stream().map(votesMapper::fromVotesEntity).collect(Collectors.toList()));
    }

    // 투표 게시글 전체 조회 기능
    @GetMapping()
    @ApiOperation(value = "투표 게시글 전체 조회", notes = "전체 투표 게시글을 조회한다.")
    public ApiResponse<List<VoteResponseDto>> findAllVotePostsList() {
        return ApiResponse.success("vote list", votePostsService.findAllVotePostsList());
    }

    // 투표 게시글 상세 조회 기능 - UUID의 경우 findBy함수를 사용할 수 없어 방안 마련해야 함
    @GetMapping("/{id}")
    @ApiOperation(value = "투표 게시글 상세 조회", notes = "하나의 유저가 작성한 투표 게시글을 조회한다.")
    public ApiResponse<VoteResponseDto> findById(@ApiParam(value = "투표 게시글 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) {
        return ApiResponse.success("vote detail", votePostsService.findById(id));
    }


    // 삭제 기능
    @DeleteMapping("/{id}")
    @ApiOperation(value = "투표 게시글 삭제", notes = "투표 게시글을 삭제한다.")
    public ApiResponse<UUID> deleteVotePost(@ApiIgnore @CurrentUser UserPrincipal userPrincipal, @ApiParam(value = "투표 게시글 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) throws UserPrincipalNotFoundException {

        if (userPrincipal == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }

        VotePosts votePosts = votePostsService.findVotePostsById(id);
        System.out.println(userPrincipal.getUserId());
        System.out.println(votePosts.getUser().getId());
        if (userPrincipal.getRoleType() != RoleType.ADMIN && !userPrincipal.getUserId().equals(votePosts.getUser().getId())) {
            throw new IllegalStateException("Current User does not match with votePosts user");
        }


        votePostsService.deleteVotePost(id);
        return ApiResponse.success("id", id);
    }
}