package efub.team4.backend_eweather.domain.vote.entity;

import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "vote_posts")
@RequiredArgsConstructor
public class VotePosts extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "vote_posts_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String building;

    @Size(max = 50)
    @NotNull
    private String clothes;

    @Formula("(SELECT COUNT(v.id) FROM vote_posts p LEFT JOIN " +
            "votes v ON v.vote_posts_id = p.vote_posts_id " +
            "WHERE p.vote_posts_id = vote_posts_id AND v.is_approved = 1)")
    private Long approvedCount;

    @Formula("(SELECT COUNT(v.id) FROM vote_posts p LEFT JOIN " +
            "votes v ON v.vote_posts_id = p.vote_posts_id " +
            "WHERE p.vote_posts_id = vote_posts_id AND v.is_approved = 0)")
    private Long disapprovedCount;


    @Builder
    public VotePosts(User user, String clothes, String building) {
        this.user = user;
        this.clothes = clothes;
        this.building = building;
    }

    public void update(String clothes) {
        this.clothes = clothes;
    }

    /*
    public void updateGood() {
        this.good++;
    }

    public void updateBad() {
        this.bad++;
    }

     */
}