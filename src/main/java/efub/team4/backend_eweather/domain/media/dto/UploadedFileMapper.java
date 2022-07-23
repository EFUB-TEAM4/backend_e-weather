package efub.team4.backend_eweather.domain.media.dto;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import org.springframework.stereotype.Component;

@Component
public class UploadedFileMapper {

    public UploadedFileDto.Response toResponseDto(UploadedFile entity) {
        if (entity == null) {
            return null;
        }

        return UploadedFileDto.Response.builder()
                .id(entity.getId())
                .name(entity.getName())
                .filetype(entity.getFileType())
                .url(entity.getUrl())
                .fileSize(entity.getFileSize())
                .build();
    }

}
