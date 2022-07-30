package efub.team4.backend_eweather.domain.auth.repository;

import efub.team4.backend_eweather.domain.auth.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(UUID userId);
    UserRefreshToken findByUserIdAndRefreshToken(UUID userId, String refreshToken);
}

