package efub.team4.backend_eweather.domain.vote.entity;


/*
	id varchar(36) not null primary key,
    user_id varchar(36) not null,
    posts_id bigint not null,
	foreign key(user_id) references user(id),
	foreign key(posts_id) references vote_posts(id)
 */

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
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 16)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @OneToOne
    @JoinColumn(name = "userId")
    User user;

    @OneToOne
    @JoinColumn(name = "postsId")
    VotePosts votePosts;

    @Builder
    public Votes(User user, VotePosts votePosts) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.votePosts = votePosts;
    }
}
