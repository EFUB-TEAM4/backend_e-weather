package efub.team4.backend_eweather.domain.sky.entity;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Sky {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "sky_id")
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String skyName;

    @Column(nullable = false)
    private Integer skyCode;

    @OneToOne
    @JoinColumn(name = "day_night_id", nullable = false)
    private DayNight dayNight;

    @URL
    private String skyBackGroundFileUrl;

    @Builder
    public Sky(String skyName, Integer skyCode, DayNight dayNight, String skyBackGroundFileUrl) {
        this.skyName = skyName;
        this.skyCode = skyCode;
        this.dayNight = dayNight;
        this.skyBackGroundFileUrl = skyBackGroundFileUrl;
    }

}
