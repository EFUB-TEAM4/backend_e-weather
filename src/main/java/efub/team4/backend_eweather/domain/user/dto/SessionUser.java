package efub.team4.backend_eweather.domain.user.dto;

import efub.team4.backend_eweather.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class SessionUser implements Serializable {

    private UUID id;
    private String fullName;
    private String email;

    @Builder
    public SessionUser(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
    }
}
