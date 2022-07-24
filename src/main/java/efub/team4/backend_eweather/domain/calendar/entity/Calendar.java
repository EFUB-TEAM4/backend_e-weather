package efub.team4.backend_eweather.domain.calendar.entity;

import com.sun.istack.NotNull;

import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
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
    @Column(length = 16, name = "calendar_id")
    private UUID id;

    /**
     * 사용자

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
*/
    /**
     * 캘린더 내용
     */

    @Lob
    @NotNull
    private String description;

    @NotNull
    private String forecastDate;

    @Column(nullable = false)
    private Integer minTemperature;

    @Column(nullable = false)
    private Integer currentTemperature;

    @Column(nullable = false)
    private Integer maxTemperature;

    @Column(nullable = false)
    private Integer rainfallPercentage;

    @OneToOne
    @JoinColumn(name = "icon_id", nullable = false)
    private Icon icon;

    @OneToOne
    @JoinColumn(name = "sky_id", nullable = false)
    private Sky sky;

    @OneToOne
    @JoinColumn(name = "pty_id", nullable = false)
    private Pty pty;

    @Builder
    public Calendar(String description, String forecastDate, Integer minTemperature, Integer currentTemperature, Integer maxTemperature, Integer rainfallPercentage, Icon icon, Sky sky, Pty pty) {
        //this.user = user;
        this.description = description;
        this.forecastDate = forecastDate;
        this.minTemperature = minTemperature;
        this.currentTemperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.rainfallPercentage = rainfallPercentage;
        this.icon = icon;
        this.sky = sky;
        this.pty = pty;
    }
}
