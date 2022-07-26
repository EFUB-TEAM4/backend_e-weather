package efub.team4.backend_eweather.domain.vote.entity;

import efub.team4.backend_eweather.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "votes")
@RequiredArgsConstructor
public class Votes {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_posts_id")
    private VotePosts votePosts;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isApproved;

    @Builder
    public Votes(User user, VotePosts votePosts, boolean isApproved) {
        this.user = user;
        this.votePosts = votePosts;
        this.isApproved = isApproved;
    }
}
