package efub.team4.backend_eweather.domain.profile.repository;

import efub.team4.backend_eweather.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Boolean existsByNickname(String nickname);

    Optional<Profile> findByNickname(String nickname);
}
