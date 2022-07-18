package efub.team4.backend_eweather.domain.user.service;

import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
    public UserResponseDto findByUserId(UUID id) {
        User user = userRepository.findById(id).get();
        return buildUserDto(user);
    }
}
