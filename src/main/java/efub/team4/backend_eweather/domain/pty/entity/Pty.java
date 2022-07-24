package efub.team4.backend_eweather.domain.pty.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Pty {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "pty_id")
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String ptyName;

    @Column(nullable = false)
    private Integer ptyCode;

    @URL
    private String ptyBackGroundFileUrl;

    @Builder
    public Pty(String ptyName, Integer ptyCode, String ptyBackGroundFileUrl) {
        this.ptyName = ptyName;
        this.ptyCode = ptyCode;
        this.ptyBackGroundFileUrl = ptyBackGroundFileUrl;
    }
}
