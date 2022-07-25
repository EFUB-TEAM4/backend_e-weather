package efub.team4.backend_eweather.domain.season.repository;

import efub.team4.backend_eweather.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
    @Query(value = "SELECT * FROM SEASON WHERE CASE WHEN season_name='겨울' THEN START_MONTH <= ?1 OR ?1 <= END_MONTH\n" +
            "    ELSE START_MONTH <= ?1 AND ?1 <= END_MONTH END", nativeQuery = true)
    Optional<Season> findByMonth(Integer month);

    Optional<Season> findSeasonBySeasonName(String name);

}
