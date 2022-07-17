package efub.team4.backend_eweather.domain.user.service;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public UserResponseDto loadUser(UUID id) {
        User user;
        if(userRepository.findById(id).isPresent()){
            user = userRepository.findById(id).get();
        }
        else{
            return null; // 예외처리 필요할 듯
        }
        return buildUserDto(user);
    }
}
