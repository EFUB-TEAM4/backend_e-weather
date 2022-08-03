package efub.team4.backend_eweather.global.config;

import efub.team4.backend_eweather.global.config.properties.CorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class CustomCorsFilter extends OncePerRequestFilter {

    private final CorsProperties corsProperties = new CorsProperties();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", corsProperties.getAllowedOrigins());
        response.setHeader("Access-Control-Allow-Methods", corsProperties.getAllowedMethods());
        response.setHeader("Access-Control-Max-Age", String.valueOf(corsProperties.getMaxAge()));
        response.setHeader("Access-Control-Allow-Headers", corsProperties.getAllowedHeaders());
        response.addHeader("Access-Control-Expose-Headers", "*");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
