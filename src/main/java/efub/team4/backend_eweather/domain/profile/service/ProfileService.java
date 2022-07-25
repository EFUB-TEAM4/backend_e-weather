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

    @Transactional(readOnly = true)
    public UUID update(UUID id, String nickname, UUID fileId) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id"));

        UploadedFile uploadedFile = uploadedFileRepository.findById(fileId)
                .orElseThrow(() -> new UploadedFileNotFoundException("UploadedFile not found with id"));

        profile.update(nickname, uploadedFile);
        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id"));

        user.updateProfile(profile);
        return id;
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

}
