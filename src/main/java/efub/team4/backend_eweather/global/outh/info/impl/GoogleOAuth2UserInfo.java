package efub.team4.backend_eweather.global.outh.info.impl;

import efub.team4.backend_eweather.global.outh.info.OAuth2UserInfo;

import java.util.Map;
import java.util.UUID;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

}

