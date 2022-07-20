package efub.team4.backend_eweather.domain.vote.entity;

import efub.team4.backend_eweather.domain.user.entity.User;
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
public class VotePosts extends BaseTimeEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 16)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column
    String building;

    @Column
    String clothes;

    @Column
    Long good;

    @Column
    Long bad;

    @Builder
    public VotePosts(UUID id, User user, String clothes,String building, Long good, Long bad) {
        this.id = id;
        this.user = user;
        this.clothes = clothes;
        this.building = building;
        this.good = good;
        this.bad = bad;
    }
}
