package efub.team4.backend_eweather.domain.sky.entity;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
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
public class Sky {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String sky_name;

    @NotEmpty
    private Integer sky_code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "day_night_id", nullable = false)
    private DayNight dayNight;
}
