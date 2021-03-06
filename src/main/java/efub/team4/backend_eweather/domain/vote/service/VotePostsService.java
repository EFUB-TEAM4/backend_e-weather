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

        votesRepository.save(votes);

        return buildResponseDto(votePosts);

    }

    // ?????? ????????? ??????
    public Long update(Long id, VoteUpdateRequestDto requestDto){
        VotePosts votePosts = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("?????? ???????????? ????????????. id " + id));

        votePosts.update(requestDto.getClothes());

        return id;
    }

    // ?????? ????????? ?????? ??????
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

    // ?????? ????????? ?????? ?????? - UUID??? ?????? findBy?????? ?????? ??????

    @Transactional
    public VoteResponseDto findById(Long id){
        VotePosts entity = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("?????? ???????????? ????????????. id " + id));
        return new VoteResponseDto(entity);

    }

    // ?????? ????????? ??????
    public void deleteVotePost (Long id){
        VotePosts votePosts = votePostsRespsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));
        votePostsRespsitory.delete(votePosts);
    }

    // ?????????
    public VoteResponseDto updateGood(Long id, SessionUser sessionUser) {
        VotePosts votePosts = votePostsRespsitory.findById(id).orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));

        List<Votes> votesList = votesRepository.findAllByVotePosts(votePosts);

        Boolean isOkay = true;
        User user = userRepository.findByEmail(sessionUser.getEmail());

        for(Votes votes : votesList){
            System.out.println(votes.getUser().getId());
            User user1 = votes.getUser();
            if(user1.equals(user)){
                isOkay = false;
                System.out.println(votes.getVotePosts().getId() + "??????");
                return null;
            }
            else{
                continue;
            }
        }
        if(!isOkay){
            return null;
        } else {
            System.out.println("??????");
            votesRepository.save(new Votes(userRepository.findByEmail(sessionUser.getEmail()), votePosts));

            votePosts.updateGood();
            VotePosts response = votePostsRespsitory.save(votePosts);
            return buildResponseDto(response);
        }
    }

    // ?????????
    public VoteResponseDto updateBad(Long id, SessionUser sessionUser) {
        VotePosts votePosts = votePostsRespsitory.findById(id).orElseThrow(() -> new IllegalArgumentException("no votePost id = " + id));
        List<Votes> votesList = votesRepository.findAllByVotePosts(votePosts);

        Boolean isOkay = true;
        User user = userRepository.findByEmail(sessionUser.getEmail());

        for(Votes votes : votesList){
            System.out.println(votes.getUser().getId());
            User user1 = votes.getUser();
            if(user1.equals(user)){
                isOkay = false;
                System.out.println(votes.getVotePosts().getId() + "??????");
                return null;
            }
            else{
                continue;
            }
        }
        if(!isOkay){
            return null;
        } else {
            System.out.println("??????");
            votesRepository.save(new Votes(userRepository.findByEmail(sessionUser.getEmail()), votePosts));

            votePosts.updateBad();
            VotePosts response = votePostsRespsitory.save(votePosts);
            return buildResponseDto(response);
        }
    }
}