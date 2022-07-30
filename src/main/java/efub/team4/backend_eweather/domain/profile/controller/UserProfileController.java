package efub.team4.backend_eweather.domain.profile.controller;

import efub.team4.backend_eweather.domain.profile.dto.ProfileDto;
import efub.team4.backend_eweather.domain.profile.dto.ProfileMapper;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.profile.service.ProfileService;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.user.service.UserService;
import efub.team4.backend_eweather.global.config.auth.CurrentUser;
import efub.team4.backend_eweather.global.dto.ApiResponse;
import efub.team4.backend_eweather.global.outh.entity.UserPrincipal;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@Api(tags = {"Profile API"}, description = "프로필 생성, 수정, 상세 조회, 조회")
@RequiredArgsConstructor
public class UserProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final ProfileMapper profileMapper;

    @PostMapping
    @ApiOperation(value = "프로필 생성", notes = "프로필을 생성한다.")
    public ApiResponse<ProfileDto.Response> createProfile(@ApiIgnore @CurrentUser UserPrincipal user,
                                                          @ApiParam(value = "프로필 생성 DTO") @RequestBody ProfileDto.CreateRequest requestDto) throws UserPrincipalNotFoundException {
        Profile profile = profileService.save(profileMapper.createReqeustDtoToEntity(user.getUserId(), requestDto));

        if (user == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }

        userService.updateProfile(user.getUserId(), profile);

        return ApiResponse.success("profile", profileMapper.fromEntity(profile));

    }

    @PutMapping
    @ApiOperation(value = "프로필 수정", notes = "프로필을 수정한다.")
    public ApiResponse<ProfileDto.Response> updateProfile(@ApiIgnore @CurrentUser UserPrincipal user,
                                                          @ApiParam(value = "프로필 수정 DTO") @RequestBody ProfileDto.CreateRequest requestDto) throws UserPrincipalNotFoundException {

        if (user == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }

        Profile profile = profileService.update(user.getUserId(), requestDto.getProfileId(), requestDto.getNickname(), requestDto.getFileId());


        return ApiResponse.success("profile", profileMapper.fromEntity(profile));
    }

    @GetMapping("/{profileId}")
    @ApiOperation(value = "프로필 상세 조회", notes = "프로필을 조회한다.")
    public ApiResponse<ProfileDto.Response> getProfile(
            @ApiParam(value = "조회할 프로필 ID",
                    required = true) @PathVariable UUID profileId) {
        Profile profile = profileService.findProfileById(profileId);
        return ApiResponse.success("profile", profileMapper.fromEntity(profile));

    }

    @GetMapping
    @ApiOperation(value = "사용자 프로필 상세 조회", notes = "사용자 프로필을 조회한다.")
    public ApiResponse<ProfileDto.Response> getProfile(
            @ApiIgnore @CurrentUser UserPrincipal user) throws UserPrincipalNotFoundException {

        if (user == null) {
            throw new UserPrincipalNotFoundException("Current User is null");
        }

        Profile profile = profileService.findProfileByUser(user.getUserId());

        return ApiResponse.success("profile", profileMapper.fromEntity(profile));

    }

    @GetMapping("/nickname/{nickname}")
    @ApiOperation(value = "닉네임으로 프로필 상세 조회", notes = "닉네임으로 프로필을 조회한다.")
    public ApiResponse<ProfileDto.Response> getProfileByNickname(
            @ApiParam(value = "조회할 사용자 닉네임",
                    required = true) @PathVariable String nickname) {

        Profile profile = profileService.findByNickname(nickname);
        return ApiResponse.success("profile", profileMapper.fromEntity(profile));

    }

}
