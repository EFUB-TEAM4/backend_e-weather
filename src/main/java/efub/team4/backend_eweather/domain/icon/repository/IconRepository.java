package efub.team4.backend_eweather.domain.icon.repository;

import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IconRepository extends JpaRepository<Icon, UUID> {
    Optional<Icon> findBySkyAndPty(Sky sky, Pty pty);

    Optional<Icon> findIconBySky_IdAndPty_Id(UUID skyId, UUID ptyId);

    Optional<Icon> findIconByIconName(String iconName);
}
