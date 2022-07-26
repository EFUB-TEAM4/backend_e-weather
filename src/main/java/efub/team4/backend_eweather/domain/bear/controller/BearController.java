package efub.team4.backend_eweather.domain.bear.controller;

import efub.team4.backend_eweather.domain.bear.dto.BearDto;
import efub.team4.backend_eweather.domain.bear.dto.BearImageResponseDto;
import efub.team4.backend_eweather.domain.bear.dto.BearMapper;
import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.service.BearService;

import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import efub.team4.backend_eweather.domain.weather.dto.BearResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bears")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"Bear API"}, description = "곰돌이 코디 조회")
public class BearController {

    @Autowired
    private final BearService bearService;
    private final BearMapper bearMapper;

    @Autowired
    private final OpenWeatherAPI openWeatherAPI;


    @ApiOperation(value = "곰돌이 목록 조회", notes = "곰돌이 목록을 조회한다.")
    public ResponseEntity<List<BearDto.BearResponseDto>> getBearList() {
        List<Bear> bearList = bearService.findAll();

        List<BearDto.BearResponseDto> responseList = bearList.stream()
                .map(bearMapper::bearResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(responseList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "곰돌이 상세 조회", notes = "ID로 곰돌이를 조회한다.")
    public ResponseEntity<BearDto.BearResponseDto> getBearById(
            @ApiParam(value = "곰돌이 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                    required = true) @PathVariable UUID id
    ) {
        Bear bear = bearService.findById(id);
        return ResponseEntity
                .ok()
                .body(bearMapper.bearResponseDto(bear));
    }

    @GetMapping("/{ptyCode}/{temperature}")
    @ApiOperation(value = "곰돌이 상세 조회", notes = "ptyCode와 기온으로 곰돌이를 조회한다.")
    public ResponseEntity<BearDto.BearResponseDto> getBearByPtyCodeAndTemperature(
            @ApiParam(value = "ptyCode", example = "1",
                    required = true) @PathVariable Integer ptyCode,
            @ApiParam(value = "temperature", example = "25",
                    required = true) @PathVariable Integer temperature
    ) {
        Bear bear = bearService.findByPtyAndTemperature(ptyCode, temperature);
        return ResponseEntity
                .ok()
                .body(bearMapper.bearResponseDto(bear));
    }

}
