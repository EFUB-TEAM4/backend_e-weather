package efub.team4.backend_eweather.domain.user.controller;

import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.service.CustomOauth2UserService;
import efub.team4.backend_eweather.domain.user.service.UserService;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import efub.team4.backend_eweather.global.outh.entity.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Api(tags = {"User API"}, description = "사용자 조회")
public class UserController {

    private final CustomOauth2UserService oauth2UserService;
    private final UserService userService;
    // 유저 정보 전체 반환
    @GetMapping
    @ApiOperation(value = "사용자 목록을 조회", notes = "사용자 목록을 조회한다.")
    public List<UserResponseDto> getUserResponseDtoList(){
        return userService.loadUsers();
    }

    @PostMapping("/postman")
    @ApiOperation(value = "사용자 등록 테스트", notes = "사용자 등록을 테스트 한다.")
    public Object sessionTest(@RequestBody Map<String, Object> attribute){
        return oauth2UserService.loadUserPostman(attribute);
    }


    @GetMapping("/currentUser")
    @ApiOperation(value = "현재 유저 확인", notes = "현재 유저를 확인한다.")
    public String getCurrentUser(@ApiIgnore Authentication authentication){
        //User user = userService.findUserByEmail();
        return "hi";
        //return user.getEmail();
    }

}
