package efub.team4.backend_eweather.domain.weather.controller;

import efub.team4.backend_eweather.domain.weather.dto.OpenWeatherResponseDto;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/weather")
public class OpenWeatherApiController {

    @Autowired
    private final OpenWeatherAPI openWeatherAPI = new OpenWeatherAPI();

    @GetMapping
    public List<OpenWeatherResponseDto> loadAllWeather() throws IOException, ParseException {
        return openWeatherAPI.findWeather();
    }


}
