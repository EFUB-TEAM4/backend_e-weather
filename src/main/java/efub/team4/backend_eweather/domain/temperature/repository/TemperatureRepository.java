package efub.team4.backend_eweather.domain.temperature.repository;

import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TemperatureRepository extends JpaRepository<Temperature, UUID> {
    @Query(value = "SELECT * FROM TEMPERATURE WHERE MIN_TEMPERATURE <= ?1 AND ?1 <= MAX_TEMPERATURE ", nativeQuery = true)
    Optional<Temperature> findByTemperature(Integer temperature);
}
