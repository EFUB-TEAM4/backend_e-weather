package efub.team4.backend_eweather;

import efub.team4.backend_eweather.global.config.properties.AppProperties;
import efub.team4.backend_eweather.global.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class, CorsProperties.class})
public class BackendEWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendEWeatherApplication.class, args);
	}

}
