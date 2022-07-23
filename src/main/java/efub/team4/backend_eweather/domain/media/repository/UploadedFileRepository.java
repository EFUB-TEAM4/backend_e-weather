package efub.team4.backend_eweather.domain.media.repository;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, UUID> {
}
