package efub.team4.backend_eweather.domain.media.service;

import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.global.utill.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileHandler {

    private final Path root = Paths.get("media", "uploads");

    public FileHandler() {
        try {
            File dir = new File("media/uploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException("File path has not been created: " + e.getMessage());
        }
    }

    /**
     * 하나의 파일 정보를 읽고 DB에 추가한 뒤 파일을 저장하는 함수
     */
    public UploadedFile parseFileInfo(MultipartFile multipartFile)
            throws IOException, IllegalStateException {

        // 파일이 없으면 빈 것을 반환
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("File is null");
        } else {

            String generateFileName = FileUtil.generateFileName(multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), this.root.resolve(generateFileName));

            // 경로 지정
            String path = "files";
            File file = new File(path);

            // 파일 생성
            UploadedFile uploadedFile = UploadedFile.builder()
                    .name(multipartFile.getOriginalFilename())
                    .url("/media" + "/" + generateFileName)
                    .fileType(multipartFile.getContentType())
                    .fileSize(multipartFile.getSize())
                    .build();

            return uploadedFile;
        }
    }
}
