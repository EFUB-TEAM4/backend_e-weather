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

                /*
                .authorizeRequests()
                .antMatchers("api/v1/weather").permitAll()
                .antMatchers("api/v1/bear").permitAll()
                .antMatchers("api/v1/votes").hasAnyRole("USER")
                .antMatchers("api/v1/calendars").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()


                 */
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(customOauth2UserService);
    }

}
