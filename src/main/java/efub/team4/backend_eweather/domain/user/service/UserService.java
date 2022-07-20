package efub.team4.backend_eweather.domain.user.service;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.global.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserResponseDto buildUserDto(User user){
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> loadUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList){
            UserResponseDto userResponseDto = buildUserDto(user);
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

    @Transactional
    public UserResponseDto getByUserId(UUID id) {
        User user = userRepository.findById(id).get();
        return buildUserDto(user);
    }

    @Transactional
    public UUID getSessionUser(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        UUID userId = user.getId();
        return userId;

    }

}
