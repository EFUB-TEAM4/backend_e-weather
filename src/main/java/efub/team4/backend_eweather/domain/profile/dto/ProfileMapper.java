package efub.team4.backend_eweather.domain.profile.dto;

import efub.team4.backend_eweather.domain.media.dto.UploadedFileMapper;
import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.media.exception.UploadedFileNotFoundException;
import efub.team4.backend_eweather.domain.media.repository.UploadedFileRepository;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.profile.repository.ProfileRepository;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    private final ProfileRepository profileRepository;
    private final UploadedFileRepository uploadedFileRepository;
    private final UserRepository userRepository;

    private final UploadedFileMapper uploadedFileMapper;

    public Profile createReqeustDtoToEntity(UUID userId, ProfileDto.CreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with user"));

        if (request.getFileId() != null && !request.getFileId().equals("")) {
            UploadedFile uploadedFile = uploadedFileRepository.findById(request.getFileId())
                    .orElseThrow(() -> new UploadedFileNotFoundException("UploadedFile not found with id"));

            return Profile.builder()
                    .user(user)
                    .nickname(request.getNickname())
                    .uploadedFile(uploadedFile)
                    .build();
        }

        return Profile.builder()
                .user(user)
                .nickname(request.getNickname())
                .build();
    }

    public ProfileDto.Response fromEntity(Profile profile) {
        return ProfileDto.Response.builder()
                .id(profile.getId())
                .userResponseDto(new UserResponseDto(profile.getUser()))
                .nickname(profile.getNickname())
                .uploadedFileDto(uploadedFileMapper.toResponseDto(profile.getUploadedFile()))
                .build();
    }
}
