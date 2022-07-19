package efub.team4.backend_eweather.domain.dayNight.entity;

import lombok.Builder;
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
public class DayNight {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String time_name;

    @Size(max = 50)
    @NotEmpty
    private String time;

    @Builder
    public DayNight(String time_name, String time) {
        this.time_name = time_name;
        this.time = time;
    }
}
