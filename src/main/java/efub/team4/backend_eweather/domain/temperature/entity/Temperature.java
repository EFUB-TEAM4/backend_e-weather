package efub.team4.backend_eweather.domain.temperature.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private Integer minTemperature;

    @NotEmpty
    private Integer maxTemperature;

    @Builder
    public Temperature(Integer minTemperature, Integer maxTemperature){
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}