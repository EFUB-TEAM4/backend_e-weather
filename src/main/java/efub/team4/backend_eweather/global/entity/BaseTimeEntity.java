package efub.team4.backend_eweather.global.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name = "created_on")
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @LastModifiedDate
    private LocalDateTime modifiedOn;

    @PrePersist
    public void onPrePersist() {
        this.createdOn = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.modifiedOn = this.createdOn;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedOn = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
