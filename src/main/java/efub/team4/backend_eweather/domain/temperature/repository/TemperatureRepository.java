package efub.team4.backend_eweather.domain.temperature.repository;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TemperatureRepository extends JpaRepository<Temperature, UUID> {
    Optional<Temperature> findByTemperature(Integer temperature);
}
