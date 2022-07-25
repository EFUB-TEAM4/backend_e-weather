package efub.team4.backend_eweather.domain.icon.controller;

import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.icon.dto.IconMapper;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.service.IconService;
import efub.team4.backend_eweather.domain.media.controller.FileUploadController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/icons")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"Icon API"}, description = "icon 생성, 조회")
public class IconController {
    private final IconService iconService;
    private final IconMapper iconMapper;

    @PostMapping
    @ApiOperation(value = "아이콘 생성", notes = "아이콘을 생성한다.")
    public ResponseEntity<IconDto.IconResponseDto> createIcon(@Valid @RequestBody IconDto.IconCreateDto requestDto) {
        Icon icon = iconService.save(iconMapper.createRequestDtoToEntity(requestDto));
        return ResponseEntity.created(
                        WebMvcLinkBuilder
                                .linkTo(FileUploadController.class)
                                .toUri())
                .body(iconMapper.iconResponseDto(icon));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "아이콘 상세 조회", notes = "아이콘을 상세 조회한다.")
    public ResponseEntity<IconDto.IconResponseDto> getIconById(@ApiParam(value = "아이콘 ID",
            required = true, example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id
    ) {
        Icon icon = iconService.findById(id);

        return ResponseEntity
                .ok()
                .body(iconMapper.iconResponseDto(icon));
    }

    @GetMapping("/{ptyCode}/{skyCode}/{forecastTime}")
    @ApiOperation(value = "아이콘 상세 조회", notes = "아이콘을 상세 조회한다.")
    public ResponseEntity<IconDto.IconResponseDto> getIconByPtyAndSky(
            @ApiParam(value = "Pty Code", required = true, example = "1") @PathVariable Integer ptyCode,
            @ApiParam(value = "Sky Code", required = true, example = "3") @PathVariable Integer skyCode,
            @ApiParam(value = "예측 시간", required = true, example = "1400") @PathVariable String forecastTime
    ) {
        Icon icon = iconService.findBySkyCodeAndPtyCode(skyCode, ptyCode, forecastTime);

        return ResponseEntity
                .ok()
                .body(iconMapper.iconResponseDto(icon));
    }

    @GetMapping
    @ApiOperation(value = "아이콘 목록 조회", notes = "아이콘 목록 조회한다.")
    public ResponseEntity<List<IconDto.IconResponseDto>> getIconList() {
        List<Icon> iconList = iconService.findAll();

        List<IconDto.IconResponseDto> responseList = iconList.stream()
                .map(iconMapper::iconResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(responseList);
    }

}
