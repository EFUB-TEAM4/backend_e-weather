package efub.team4.backend_eweather.domain.user.service;

import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import efub.team4.backend_eweather.domain.user.entity.User;

import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public UserResponseDto buildUserDto(User user) {
        return new UserResponseDto(user);
    }

    @Transactional
    public List<UserResponseDto> loadUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
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
    public User getSessionUser() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        // 세션 저장 됐는지 확인
        System.out.println("--- This is the current User Info ---");
        System.out.println(sessionUser.getId());
        System.out.println(sessionUser.getEmail());
        System.out.println(sessionUser.getFullName()); // utf-8
        System.out.println("--- This is the current User Info ---");
        User user = userRepository.findByEmail(sessionUser.getEmail());
        return user;
    }

    @Transactional
    public void updateProfile(UUID userId, Profile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id"));

        user.updateProfile(profile);
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id"));
    }
}
