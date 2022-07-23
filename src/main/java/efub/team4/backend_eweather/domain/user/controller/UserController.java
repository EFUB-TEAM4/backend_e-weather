package efub.team4.backend_eweather.domain.user.controller;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.service.CustomOauth2UserService;
import efub.team4.backend_eweather.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/postman")
    public Object sessionTest(@RequestBody Map<String, Object> attribute){
        return oauth2UserService.loadUserPostman(attribute);
    }
}
