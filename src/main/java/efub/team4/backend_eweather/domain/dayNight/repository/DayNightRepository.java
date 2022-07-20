package efub.team4.backend_eweather.domain.dayNight.repository;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DayNightRepository extends JpaRepository<DayNight, UUID> {
    Optional<DayNight> findByTime(String time);
}
