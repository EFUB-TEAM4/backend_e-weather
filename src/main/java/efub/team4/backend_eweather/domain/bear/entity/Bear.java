package efub.team4.backend_eweather.domain.bear.entity;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.temperature.entity.Temperature;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Bear {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "bear_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "temperature_id", nullable = false)
    private Temperature temperature;

    @OneToOne
    @JoinColumn(name = "pty_id", nullable = false)
    private Pty pty;

    @Column(nullable = false)
    private String clothName;

    @URL
    private String bearFileUrl;

    @Builder
    public Bear(Temperature temperature, Pty pty, String clothName, String bearFileUrl){
        this.temperature = temperature;
        this.pty = pty;
        this.clothName = clothName;
        this.bearFileUrl = bearFileUrl;
    }


}
