package efub.team4.backend_eweather.domain.profile.dto;

import com.sun.istack.NotNull;
import lombok.*;

public class ProfileDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull
        private String nickname;

    }
}
