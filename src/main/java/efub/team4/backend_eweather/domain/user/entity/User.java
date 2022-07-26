package efub.team4.backend_eweather.domain.user.entity;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.profile.entity.Profile;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @Builder
    public User(UUID id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }


    public Object update(String name) {
        this.fullName = name;
        return this;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }
}
