package efub.team4.backend_eweather.domain.profile.dto;

import efub.team4.backend_eweather.domain.media.dto.UploadedFileDto;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import io.swagger.annotations.ApiModelProperty;
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
        @ApiModelProperty(value = "등록할 닉네임", example = "efub", required = true)
        private String nickname;

        @ApiModelProperty(value = "업로드된 File ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID fileId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @ApiModelProperty(value = "Profile ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "사용자 정보", required = true)
        private UserResponseDto userResponseDto;

        @ApiModelProperty(value = "등록한 닉네임", example = "efub", required = true)
        private String nickname;

        @ApiModelProperty(value = "업로드된 프로필 사진 정보", required = true)
        private UploadedFileDto.Response uploadedFileDto;
    }
}
