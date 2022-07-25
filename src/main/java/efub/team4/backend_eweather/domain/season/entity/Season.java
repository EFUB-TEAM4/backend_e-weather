package efub.team4.backend_eweather.domain.season.entity;

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
public class Season {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16, name = "season_id")
    private UUID id;

    @Column(nullable = false)
    private Integer startMonth;

    @Column(nullable = false)
    private Integer endMonth;

    @Size(max = 50)
    @NotEmpty
    private String seasonName;

    @URL
    private String seasonBackGroundFileUrl;

    @Builder
    public Season(Integer startMonth, Integer endMonth, String seasonName, String seasonsBackGroundFileUrl){
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.seasonName = seasonName;
        this.seasonBackGroundFileUrl = seasonsBackGroundFileUrl;
    }


}
