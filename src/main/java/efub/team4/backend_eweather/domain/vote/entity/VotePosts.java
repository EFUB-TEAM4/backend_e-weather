package efub.team4.backend_eweather.domain.vote.entity;

import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "vote_posts")
@RequiredArgsConstructor
public class VotePosts extends BaseTimeEntity{

/*    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 16)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;*/

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 16)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

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
    public VotePosts(UUID id, User user, String clothes,String building) {
        this.id = id;
        this.user = user;
        this.clothes = clothes;
        this.building = building;
        this.good = Long.valueOf(0);
        this.bad = Long.valueOf(0);
    }

    public void update(String clothes) {
        this.clothes = clothes;
    }

    public void updateGood(){
        this.good++;
    }

    public void updateBad(){
        this.bad++;
    }
}