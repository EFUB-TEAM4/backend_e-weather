package efub.team4.backend_eweather.global.config.auth;

import efub.team4.backend_eweather.domain.user.service.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images", "/js/**", "/profile")
                .hasAnyRole()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/success")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/success")
                .userInfoEndpoint()
                .userService(customOauth2UserService);
    }

}
