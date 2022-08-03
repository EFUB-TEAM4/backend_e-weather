package efub.team4.backend_eweather.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:/var/www/media/");
    }

    /**
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(corsProperties.getAllowedOrigins())
                .allowedMethods(corsProperties.getAllowedMethods())
                .allowedHeaders(corsProperties.getAllowedHeaders())
                .allowCredentials(true)
                .maxAge(corsProperties.getMaxAge());
    }**/
}
