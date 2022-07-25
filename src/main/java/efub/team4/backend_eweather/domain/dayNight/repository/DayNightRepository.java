package efub.team4.backend_eweather.domain.dayNight.repository;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DayNightRepository extends JpaRepository<DayNight, UUID> {
    @Query(value = "SELECT * FROM DAY_NIGHT  WHERE CASE WHEN TIME_NAME='day' THEN START_TIME <= STR_TO_DATE(?1,'%H%i') and STR_TO_DATE(?1,'%H%i') < END_TIME WHEN TIME_NAME ='night' THEN START_TIME <= STR_TO_DATE(?1,'%H%i') or STR_TO_DATE(?1,'%H%i') < END_TIME END", nativeQuery = true)
    Optional<DayNight> findDayNightWithQueryByTime(String time);

    Optional<DayNight> findByTimeName(String name);
}