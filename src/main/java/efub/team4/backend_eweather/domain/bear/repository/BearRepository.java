package efub.team4.backend_eweather.domain.bear.repository;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BearRepository extends JpaRepository<Bear, UUID> {
    Optional<Bear> findByTemperatureAndPtyAndSeason(Temperature temperature, Pty pty, Season season);

}
