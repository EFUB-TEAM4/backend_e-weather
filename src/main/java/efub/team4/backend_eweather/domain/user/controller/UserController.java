package efub.team4.backend_eweather.domain.user.controller;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.service.CustomOauth2UserService;
import efub.team4.backend_eweather.domain.user.service.UserService;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import efub.team4.backend_eweather.global.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CustomOauth2UserService oauth2UserService;
    private final UserService userService;
    // 유저 정보 전체 반환
    @GetMapping
    public List<UserResponseDto> getUserResponseDtoList(){
        return userService.loadUsers();
    }

    @GetMapping("/account")
    public UserResponseDto getCurrentUser(@LoginUser SessionUser sessionUser){
        return userService.getByUserId(sessionUser.getId());
    }

    @PostMapping("/postman")
    public Object sessionTest(@RequestBody Map<String, Object> attribute){
        return oauth2UserService.loadUserPostman(attribute);
    }

}
