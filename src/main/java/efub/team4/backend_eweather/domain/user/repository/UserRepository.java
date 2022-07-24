package efub.team4.backend_eweather.domain.user.repository;

import efub.team4.backend_eweather.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

}
