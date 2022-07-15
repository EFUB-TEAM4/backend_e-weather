package efub.team4.backend_eweather.domain.user.dto;

import efub.team4.backend_eweather.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // Oauth2User에서 반환하는 사용자 정보는 Map -> 변환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }



    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // User 엔티티 생성
    // 엔티티 생성 시점은 처음 가입할 때
    // 가입할 때 기본 권한은 GUEST ..-> 한 번 바꿔 볼까?
    // OAuthAttributes 클래스 생성이 끝나면 같은 패키지에 SessionUser 생성
    public User toEntity() {
        return User.builder()
                .fullName(name)
                .email(email)
                .build();
    }

}
