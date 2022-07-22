package efub.team4.backend_eweather.domain.vote.repository;

import efub.team4.backend_eweather.domain.vote.entity.VotePosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD:src/main/java/efub/team4/backend_eweather/domain/vote/repository/VotePostsRespsitory.java
public interface VotePostsRespsitory extends JpaRepository<VotePosts, Long> {

    // void deleteVotePost(VotePosts votePosts);
}
=======
public interface DayNightRepository extends JpaRepository<DayNight, UUID> {
    @Query(value = "SELECT * FROM DAY_NIGHT  WHERE CASE WHEN TIME_NAME='day' THEN START_TIME <= ?1 and ?1 < END_TIME WHEN TIME_NAME ='night' THEN START_TIME <= ?1 or ?1 < END_TIME END", nativeQuery = true)
    Optional<DayNight> findDayNightWithQueryByTime(String time);
}
>>>>>>> user:src/main/java/efub/team4/backend_eweather/domain/dayNight/repository/DayNightRepository.java
