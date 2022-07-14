package efub.team4.backend_eweather.domain.calendar.entity;

import com.sun.istack.NotNull;
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
    @Column(length = 16)
    private UUID id;

    @Lob
    @NotNull
    private String description;

    @NotNull
    private Integer temperature;

    @NotNull
    private Integer max_temperature;

    @NotNull
    private Integer min_temperature;

    @Column(precision = 10, scale = 4)
    private BigDecimal pty_probability;

}
