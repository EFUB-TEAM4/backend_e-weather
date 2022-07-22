package efub.team4.backend_eweather.domain.icon.dayNight.repository;

import efub.team4.backend_eweather.domain.icon.dayNight.entity.DayNight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DayNightRepository extends JpaRepository<DayNight, UUID> {
    @Query(value = "SELECT * FROM DAY_NIGHT  WHERE CASE WHEN TIME_NAME='day' THEN START_TIME <= ?1 and ?1 < END_TIME WHEN TIME_NAME ='night' THEN START_TIME <= ?1 or ?1 < END_TIME END", nativeQuery = true)
    Optional<DayNight> findDayNightWithQueryByTime(String time);
}