package efub.team4.backend_eweather.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class HeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        if (StringUtils.hasText(headerValue) && headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(7, headerValue.length());
        }

        return null;
    }
}
