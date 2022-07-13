package efub.team4.backend_eweather.global.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
public class SpringFoxConfig {
    public static final Contact DEFAULT_CONTACT =
            new Contact(
                    "E-Weather",
                    "https://github.com/EFUB-TEAM4",
                    "lsr_1105@naver.com");

    public static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo(
                    "E-Weather REST API",
                    "Swagger documentation for E-Weather REST API.",
                    "1.0.0",
                    null,
                    DEFAULT_CONTACT,
                    null,
                    null,
                    Arrays.asList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}