package efub.team4.backend_eweather.domain.media.service;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.media.exception.UploadedFileNotFoundException;
import efub.team4.backend_eweather.domain.media.repository.UploadedFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadedFileService {
    private final UploadedFileRepository uploadedFileRepository;
    private final FileHandler fileHandler;

    @Transactional
    public UploadedFile create(MultipartFile multipartFile)
            throws Exception {

        UploadedFile uploadedFile = fileHandler.parseFileInfo(multipartFile);
        uploadedFile = uploadedFileRepository.save(uploadedFile);
        return uploadedFile;
    }

    @Transactional
    public UploadedFile create(UploadedFile entity) {
        return uploadedFileRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public UploadedFile findById(final UUID id) {

        return uploadedFileRepository.findById(id)
                .orElseThrow(() -> new UploadedFileNotFoundException("UploadedFile not found with id=" + id.toString()));
    }
}
