package efub.team4.backend_eweather.domain.icon.controller;

import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.icon.dto.IconMapper;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.service.IconService;
import efub.team4.backend_eweather.domain.media.controller.FileUploadController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/icons")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"Icon API"})
public class IconController {
    private final IconService iconService;
    private final IconMapper iconMapper;

    @PostMapping
    public ResponseEntity<IconDto.IconResponseDto> createIcon(@Valid @RequestBody IconDto.IconCreateDto requestDto) {
        Icon icon = iconService.save(iconMapper.createRequestDtoToEntity(requestDto));
        return ResponseEntity.created(
                        WebMvcLinkBuilder
                                .linkTo(FileUploadController.class)
                                .toUri())
                .body(iconMapper.iconResponseDto(icon));
    }
}
