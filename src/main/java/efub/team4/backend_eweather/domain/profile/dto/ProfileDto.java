package efub.team4.backend_eweather.domain.profile.dto;

import efub.team4.backend_eweather.domain.media.dto.UploadedFileDto;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class ProfileDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotEmpty
        private String nickname;
        @NotEmpty
        private UUID fileId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID id;
        private UserResponseDto userResponseDto;
        private String nickname;
        private UploadedFileDto.Response uploadedFileDto;
    }
}
