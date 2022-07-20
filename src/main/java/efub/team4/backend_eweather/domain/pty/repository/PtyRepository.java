package efub.team4.backend_eweather.domain.pty.repository;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PtyRepository extends JpaRepository<Pty, UUID> {
    Optional<Pty> findByPtyCode(Integer ptyCode);
}
