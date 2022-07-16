package efub.team4.backend_eweather.domain.user.dto;

import efub.team4.backend_eweather.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {
    private UUID id;
    private String email;
    private String fullName;
    private LocalDateTime createOn;
    private LocalDateTime modifiedOn;

    @Builder
    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.createOn = user.getCreatedOn();

        if(user.getModifiedOn() != null){
            this.modifiedOn = user.getModifiedOn();
        }
        else{
            this.modifiedOn = null;
        }

    }

}
