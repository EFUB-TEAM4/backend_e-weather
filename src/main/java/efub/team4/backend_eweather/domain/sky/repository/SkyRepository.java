package efub.team4.backend_eweather.domain.sky.repository;

import efub.team4.backend_eweather.domain.sky.entity.Sky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SkyRepository extends JpaRepository<Sky, UUID> {
    Optional<Sky> findBySkyName(String skyName);
    Optional<Sky> findBySkyCode(Integer skyCode);
}
