package efub.team4.backend_eweather.domain.user.service;

import efub.team4.backend_eweather.domain.user.dto.OAuthAttributes;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());
        System.out.println();

        String hd = (String) oAuth2User.getAttribute("hd");

        if(!Objects.equals(hd, "ewhain.net")){
            return null;
        }

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );

    }

    public User saveOrUpdate(OAuthAttributes attributes){

        /*
        User user = userRepository.findByEmail(attributes.getEmail());

        if(user != null){
            return user;
        } else {
            userRepository.save(attributes.toEntity());
            user = userRepository.findByEmail(attributes.getEmail());
            return user;
        }

         */

        User user = (User) userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);

    }

    public Object loadUserPostman(Map<String, Object> attribute) {
        OAuthAttributes attributes = OAuthAttributes.ofGoogle((String) attribute.get("sub"), attribute);
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        return httpSession.getAttribute("user");
    }
}
