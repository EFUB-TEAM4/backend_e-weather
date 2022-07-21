package efub.team4.backend_eweather.domain.icon.entity;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
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
public class Icon {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "icon_id")
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String iconName;

    @OneToOne
    @JoinColumn(name = "sky_id", nullable = false)
    private Sky sky;

    @OneToOne
    @JoinColumn(name = "pty_id", nullable = false)
    private Pty pty;

    @Builder
    public Icon(String iconName, Sky sky, Pty pty) {
        this.iconName = iconName;
        this.sky = sky;
        this.pty = pty;
    }

}
