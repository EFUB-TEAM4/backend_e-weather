package efub.team4.backend_eweather.domain.temperature.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Temperature {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @NotEmpty
    private Integer min_temperature;

    @NotEmpty
    private Integer max_temperature;

    @Builder
    public Temperature(Integer min_temperature, Integer max_temperature){
        this.min_temperature = min_temperature;
        this.max_temperature = max_temperature;
    }
}
