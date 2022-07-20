package efub.team4.backend_eweather.domain.pty.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(length = 16)
    private UUID id;

    @Size(max = 50)
    @NotEmpty
    private String ptyName;

    @NotEmpty
    private Integer ptyCode;

    @Builder
    public Pty(String ptyName, Integer ptyCode){
        this.ptyName = ptyName;
        this.ptyCode = ptyCode;
    }
}
