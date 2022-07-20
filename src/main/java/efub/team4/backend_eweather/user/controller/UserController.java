package efub.team4.backend_eweather.user.controller;

import efub.team4.backend_eweather.user.dto.UserResponseDto;
import efub.team4.backend_eweather.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    // 유저 정보 전체 반환
    @GetMapping
    public List<UserResponseDto> getUserResponseDtoList(){
        return userService.loadUsers();
    }
}
