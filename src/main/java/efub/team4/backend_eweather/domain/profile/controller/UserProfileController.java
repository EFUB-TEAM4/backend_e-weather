package efub.team4.backend_eweather.domain.profile.controller;

import efub.team4.backend_eweather.domain.profile.dto.ProfileDto;
import efub.team4.backend_eweather.domain.profile.dto.ProfileMapper;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.profile.service.ProfileService;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@Api(tags = {"Profile API"})
@RequiredArgsConstructor
public class UserProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @PostMapping
    @ApiOperation(value = "프로필 생성", notes = "프로필을 생성한다.")
    public ResponseEntity<ProfileDto.Response> createProfile(@LoginUser SessionUser user,
                                                             @RequestBody ProfileDto.CreateRequest requestDto) {
        Profile profile = profileService.save(profileMapper.createReqeustDtoToEntity(user.getId(), requestDto));

        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(UserProfileController.class)
                                .slash(profile.getId())
                                .toUri())
                .body(profileMapper.fromEntity(profile));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "프로필 수정", notes = "프로필을 수정한다.")
    public ResponseEntity<ProfileDto.Response> updateProfile(@LoginUser SessionUser user,
                                                             @RequestBody ProfileDto.CreateRequest requestDto) {
        UUID profileId = profileService.update(user.getId(), requestDto.getNickname(), requestDto.getFileId());

        Profile profile = profileService.findProfileById(profileId);

        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(UserProfileController.class)
                                .slash(profile.getId())
                                .toUri())
                .body(profileMapper.fromEntity(profile));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "프로필 상세 조회", notes = "프로필을 조회한다.")
    public ResponseEntity<ProfileDto.Response> getProfile(
            @ApiParam(value = "Profile ID",
                    required = true) @PathVariable UUID id) {
        Profile profile = profileService.findProfileById(id);
        return ResponseEntity
                .ok()
                .body(profileMapper.fromEntity(profile));
    }

    @GetMapping
    @ApiOperation(value = "사용자 프로필 상세 조회", notes = "사용자 프로필을 조회한다.")
    public ResponseEntity<ProfileDto.Response> getProfile(
            @ApiParam(value = "User",
                    required = true) @LoginUser SessionUser user) {
        Profile profile = profileService.findProfileByUser(user.getId());

        return ResponseEntity
                .ok()
                .body(profileMapper.fromEntity(profile));
    }

}
