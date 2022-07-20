package efub.team4.backend_eweather.user.service;

import efub.team4.backend_eweather.user.dto.UserResponseDto;
import efub.team4.backend_eweather.user.entity.User;
import efub.team4.backend_eweather.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

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

}
