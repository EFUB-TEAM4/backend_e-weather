package efub.team4.backend_eweather.domain.profile.repository;

import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findProfileByUser(User user);
    Optional<Profile> findProfileByNickname(String nickname);
}
