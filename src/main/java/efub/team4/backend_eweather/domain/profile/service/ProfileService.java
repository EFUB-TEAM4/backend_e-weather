package efub.team4.backend_eweather.domain.profile.service;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.media.exception.UploadedFileNotFoundException;
import efub.team4.backend_eweather.domain.media.repository.UploadedFileRepository;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.profile.exception.ProfileAlreadyExistsException;
import efub.team4.backend_eweather.domain.profile.exception.ProfileNotFoundException;
import efub.team4.backend_eweather.domain.profile.repository.ProfileRepository;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UploadedFileRepository uploadedFileRepository;
    private final UserRepository userRepository;

    @Transactional
    public Profile save(Profile profile) {
        profileRepository.findProfileByUser(profile.getUser())
                .ifPresent((existedProfile) -> {
                    throw new ProfileAlreadyExistsException("Profile already exists with user");
                });
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile update(UUID userId, UUID profileId, String nickname, UUID fileId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id"));

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id"));

        if (!userId.equals(profile.getUser().getId())){
            throw new IllegalStateException("UserId does not match with profile id");
        }

        if (fileId != null && !fileId.equals("")) {
            UploadedFile uploadedFile = uploadedFileRepository.findById(fileId)
                    .orElseThrow(() -> new UploadedFileNotFoundException("UploadedFile not found with id"));
            profile.update(nickname, uploadedFile);
        } else {
            profile.update(nickname, null);
        }

        Profile updatedProfile = profileRepository.save(profile);

        return updatedProfile;
    }

    @Transactional(readOnly = true)
    public Profile findProfileById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id"));

        return profile;
    }

    @Transactional(readOnly = true)
    public Profile findProfileByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id"));

        Profile profile = profileRepository.findProfileByUser(user)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with user"));

        return profile;
    }

    @Transactional(readOnly = true)
    public Profile findByNickname(String nickname) {
        Profile profile = profileRepository.findProfileByNickname(nickname)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with nickname"));

        return profile;
    }

}
