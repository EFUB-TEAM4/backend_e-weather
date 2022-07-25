package efub.team4.backend_eweather.domain.profile.entity;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Profile extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "profile_id")
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "uploaded_file_id")
    private UploadedFile uploadedFile;

    @Builder
    public Profile(String nickname, User user, UploadedFile uploadedFile) {
        this.nickname = nickname;
        this.user = user;
        this.uploadedFile = uploadedFile;
    }

    public void update(String nickname, UploadedFile uploadedFile){
        this.nickname = nickname;
        this.uploadedFile = uploadedFile;
    }
}
