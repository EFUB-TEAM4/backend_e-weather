package efub.team4.backend_eweather.domain.vote.service;

import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.domain.user.service.UserService;
import efub.team4.backend_eweather.domain.vote.dto.VoteRequestDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteResponseDto;
import efub.team4.backend_eweather.domain.vote.dto.VoteUpdateRequestDto;
import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import efub.team4.backend_eweather.domain.vote.entity.Votes;
import efub.team4.backend_eweather.domain.vote.repository.VotePostsRespsitory;
import efub.team4.backend_eweather.domain.vote.repository.VotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VotePostsService {

    @Autowired
    private final VotePostsRespsitory votePostsRespsitory;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final VotesRepository votesRepository;

    public VoteResponseDto buildResponseDto(VotePosts entity){
        return new VoteResponseDto(entity);
    }

    @Transactional
    public VoteResponseDto savePost(SessionUser user, VoteRequestDto voteRequestDto) {
        // voteRequestDto -> entity

        UUID id = user.getId();

        User writer = userRepository.findById(id).get();

        VotePosts votePosts = VotePosts.builder()
                .user(writer)
                .building(voteRequestDto.getBuilding())
                .clothes(voteRequestDto.getClothes())
                .build();

        votePostsRespsitory.save(votePosts);

        Votes votes = Votes.builder()
                .user(writer)
                .votePosts(votePosts)
                .build();

        return buildResponseDto(votePosts);

    }

    // 투표 게시글 수정
    public Long update(Long id, VoteUpdateRequestDto requestDto){
        VotePosts votePosts = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id " + id));

        votePosts.update(requestDto.getClothes());

        return id;
    }

    // 투표 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<VoteResponseDto> findAllVotePostsList(){

        List<VotePosts> votePostsList = votePostsRespsitory.findAll();
        List<VoteResponseDto> voteResponseDtoList = new ArrayList<>();
        for (VotePosts votePost : votePostsList) {
            VoteResponseDto voteResponsetDto = new VoteResponseDto(votePost);
            voteResponseDtoList.add(voteResponsetDto);
        }
        return voteResponseDtoList;
    }

    // 투표 게시글 개별 조회 - UUID의 경우 findBy함수 사용 불가

    @Transactional
    public VoteResponseDto findById(Long id){
        VotePosts entity = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id " + id));
        return new VoteResponseDto(entity);

    }

    // 투표 게시글 삭제
    public void deleteVotePost (Long id){
        VotePosts votePosts = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));
        votePostsRespsitory.delete(votePosts);
    }

    // 좋아요
    public VoteResponseDto updateGood(Long id, SessionUser sessionUser) {
        VotePosts votePosts = votePostsRespsitory.findById(id).orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));

        List<Votes> votesList = votesRepository.findAllByVotePosts(votePosts);

        Boolean isOkay = true;
        UUID userId = sessionUser.getId();

        for(Votes votes : votesList){
            if(votes.getUser().getId() == userId){
                isOkay = false;
                break;
            }
            else{
                continue;
            }
        }
        if(!isOkay){
            return null;
        }


        votesRepository.save(new Votes(userRepository.findByEmail(sessionUser.getEmail()), votePosts));

        votePosts.updateGood();
        VotePosts response = votePostsRespsitory.save(votePosts);



        return buildResponseDto(response);
    }

    // 싫어요
    public VoteResponseDto updateBad(Long id, SessionUser sessionUser) {
        VotePosts votePosts = votePostsRespsitory.findById(id).orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));
        votePosts.updateBad();
        VotePosts response = votePostsRespsitory.save(votePosts);
        return buildResponseDto(response);
    }
}