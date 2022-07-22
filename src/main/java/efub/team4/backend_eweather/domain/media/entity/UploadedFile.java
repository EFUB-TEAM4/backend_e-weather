package efub.team4.backend_eweather.domain.media.entity;

import efub.team4.backend_eweather.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UploadedFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fileType;

    @URL
    @Column(nullable = false)
    private String url;

    @Column
    private Long fileSize;

    @Builder
    public UploadedFile(String name, String fileType, String url, Long fileSize) {
        this.name = name;
        this.fileType = fileType;
        this.url = url;
        this.fileSize = fileSize;
    }
}