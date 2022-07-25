package efub.team4.backend_eweather.domain.eweather.controller;

import efub.team4.backend_eweather.domain.eweather.dto.EweatherDto;
import efub.team4.backend_eweather.domain.eweather.dto.EweatherMapper;
import efub.team4.backend_eweather.domain.eweather.entity.Eweather;
import efub.team4.backend_eweather.domain.eweather.service.EweatherService;
import efub.team4.backend_eweather.domain.media.controller.FileUploadController;
import efub.team4.backend_eweather.domain.weather.dto.CurrentWeatherResponseDto;
import efub.team4.backend_eweather.domain.weather.dto.ForcastResponseDto;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/eweathers")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"EWeather API"})
public class EweatherController {
    private final OpenWeatherAPI openWeatherAPI;
    private final EweatherService eweatherService;

    private final EweatherMapper eweatherMapper;

    @GetMapping("/current")
    @ApiOperation(value = "현재 날씨 정보값 조회", notes = "현재 날씨 정보값을 조회한다.")
    public ResponseEntity<EweatherDto.CurrentResponseDto> getCurrentWeather() {
        try {
            CurrentWeatherResponseDto currentWeather = openWeatherAPI.findCurrentWeather();
            Eweather eweather = eweatherService.create(currentWeather);
            return ResponseEntity.created(
                            WebMvcLinkBuilder
                                    .linkTo(FileUploadController.class)
                                    .toUri())
                    .body(eweatherMapper.fromEntity(eweather));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could Not Load Current Weather Resources");
        }
    }


    @GetMapping("/forecast")
    @ApiOperation(value = "하루 일기 예보 조회", notes = "하루 일기 예보 목록을 조회한다.")
    public ResponseEntity<List<EweatherDto.ForecastWeatherResponseDto>> getForcastWeatherList() {
        try {
            List<ForcastResponseDto> forcastWeather = openWeatherAPI.findForcastWeather();

            List<EweatherDto.ForecastWeatherResponseDto> forecastLists = forcastWeather.stream()
                    .map(eweatherMapper::fromForecasts)
                    .collect(Collectors.toList());

            return ResponseEntity.created(
                            WebMvcLinkBuilder
                                    .linkTo(EweatherController.class)
                                    .toUri())
                    .body(forecastLists);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could Not Load Current Weather Resources");
        }
    }

}
