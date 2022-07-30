package efub.team4.backend_eweather.domain.user.entity;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import efub.team4.backend_eweather.global.outh.entity.ProviderType;
import efub.team4.backend_eweather.global.outh.entity.RoleType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Column
    private String email;

    @Column
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Profile profile;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Builder
    public User(String email, String fullName, ProviderType providerType, RoleType roleType) {
        this.email = email;
        this.fullName = fullName;
        this.providerType = providerType;
        this.roleType = roleType;
    }

    public User(String subject, String email, Collection<? extends GrantedAuthority> authorities) {
        super();
    }


    public Object update(String name) {
        this.fullName = name;
        return this;
    }

    public void updateUserName(String fullName) {
        this.fullName = fullName;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }
}
