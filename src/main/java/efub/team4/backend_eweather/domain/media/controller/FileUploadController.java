package efub.team4.backend_eweather.domain.media.controller;

import efub.team4.backend_eweather.domain.media.dto.UploadedFileDto;
import efub.team4.backend_eweather.domain.media.dto.UploadedFileMapper;
import efub.team4.backend_eweather.domain.media.entity.UploadedFile;
import efub.team4.backend_eweather.domain.media.exception.UploadedFileInvalidException;
import efub.team4.backend_eweather.domain.media.service.S3Service;
import efub.team4.backend_eweather.domain.media.service.UploadedFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.ion.IonException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/media")
@Api(tags = {"S3 파일 업로드 API"})
@RequiredArgsConstructor
@Slf4j
public class FileUploadController {
    private final UploadedFileMapper uploadedFileMapper;
    private final UploadedFileService uploadedFileService;
    private final S3Service s3Service;


    @PostMapping
    @ApiOperation(value = "파일 업로드", notes = "S3 버킷에 한 개의 파일을 업로드한다.")
    public ResponseEntity<UploadedFileDto.Response> create(
            @ApiParam(value = "업로드할 파일", required = true) @RequestParam("file") MultipartFile file) throws IonException {

        try {
            String url = s3Service.uploadFile(file, "upload/profile");
            UploadedFile uploadedFile = uploadedFileService.create(UploadedFile.builder()
                    .name(file.getOriginalFilename())
                    .fileSize(file.getSize())
                    .url(url)
                    .fileType(file.getContentType())
                    .build());

            return ResponseEntity.created(
                            WebMvcLinkBuilder
                                    .linkTo(FileUploadController.class)
                                    .toUri())
                    .body(uploadedFileMapper.toResponseDto(uploadedFile));

        } catch (Exception e) {
            e.printStackTrace();
            throw new UploadedFileInvalidException("Could not upload file.");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "S3 버킷에 업로드된 파일 상세 조회", notes = "업로드된 파일 하나에 대한 상세 정보를 조회한다.")
    public ResponseEntity<UploadedFileDto.Response> get(
            @ApiParam(value = "업로드된 파일 ID", required = true) @PathVariable UUID id) {

        UploadedFile uploadedFile = uploadedFileService.findById(id);

        return ResponseEntity
                .ok()
                .body(uploadedFileMapper.toResponseDto(uploadedFile));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "S3 버킷에 업로드된 파일 상세 조회", notes = "업로드된 파일 하나에 대한 상세 정보를 조회한다.")
    public ResponseEntity<UploadedFileDto.Response> delete(
            @ApiParam(value = "업로드된 파일 ID", required = true) @PathVariable UUID id) {

        UploadedFile uploadedFile = uploadedFileService.findById(id);
        s3Service.deleteFileFromS3Bucket(uploadedFile.getUrl());

        return ResponseEntity
                .ok()
                .body(uploadedFileMapper.toResponseDto(uploadedFile));
    }
}
