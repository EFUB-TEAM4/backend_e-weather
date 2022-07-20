package efub.team4.backend_eweather.domain.vote.entity;

import efub.team4.backend_eweather.user.entity.User;
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
public class votePosts extends BaseTimeEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 16)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @OneToMany
    User user;

    @Column
    String building;

    @Column
    String clothes;

    @Column
    Long like;

    @Column
    Long dislike;

    @Builder
    public votePosts(UUID id, User user, String building, Long like, Long dislike) {
        this.id = id;
        this.user = user;
        this.building = building;
        this.like = like;
        this.dislike = dislike;
    }
}
