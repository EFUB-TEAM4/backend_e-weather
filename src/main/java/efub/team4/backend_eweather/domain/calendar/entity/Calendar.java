package efub.team4.backend_eweather.domain.calendar.entity;

import com.sun.istack.NotNull;
import efub.team4.backend_eweather.domain.user.entity.User;
import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Calendar extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    /**
     * 사용자
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 캘린더 내용
     */
    @Lob
    @NotNull
    private String description;

    /**
     * 기온
     */
    @NotNull
    private Integer temperature;

    /**
     * 최고 기온
     */
    @NotNull
    private Integer max_temperature;

    /**
     * 최저 기온
     */
    @NotNull
    private Integer min_temperature;

    /**
     * 강수 확률
     */
    @Column(precision = 10, scale = 4)
    private BigDecimal pty_probability;

}
