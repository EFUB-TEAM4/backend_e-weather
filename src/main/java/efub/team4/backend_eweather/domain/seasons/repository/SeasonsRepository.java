package efub.team4.backend_eweather.domain.seasons.repository;

import efub.team4.backend_eweather.domain.seasons.entity.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SeasonsRepository extends JpaRepository<Seasons, UUID> {

}
