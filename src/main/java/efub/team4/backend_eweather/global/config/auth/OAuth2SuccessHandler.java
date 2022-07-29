package efub.team4.backend_eweather.global.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // 아 제발 이걸로 걍 되라
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> google = (Map<String, Object>) oAuth2User.getAttributes();
        getRedirectStrategy().sendRedirect(request, response, makeRedirectUrl());
    }

    private String makeRedirectUrl(){
        return UriComponentsBuilder.fromUriString("http://localhost:3000/").build().toUriString();
    }

}
