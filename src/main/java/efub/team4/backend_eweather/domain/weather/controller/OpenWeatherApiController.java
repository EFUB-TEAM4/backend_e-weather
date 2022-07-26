package efub.team4.backend_eweather.domain.weather.controller;

import efub.team4.backend_eweather.domain.weather.dto.*;
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

    private final OpenWeatherAPI openWeatherAPI = new OpenWeatherAPI();

    @GetMapping
    public List<OpenWeatherResponseDto> loadAllWeather() throws IOException, ParseException {
        return openWeatherAPI.findWeather();
    }

    @GetMapping("/forcast")
    public List<ForcastResponseDto> loadForcastWeather() throws IOException, ParseException{
        return openWeatherAPI.findForcastWeather();
    }

    @GetMapping("/calendar")
    public CalendarWeatherResponseDto loadCalendarWeather() throws IOException, ParseException{
        return openWeatherAPI.findCalendarWeather();
    }

    @GetMapping("/tmp")
    public WeatherResponseDto loadTemperature() throws IOException, ParseException{
        return openWeatherAPI.findTemperature();
    }

    @GetMapping("/sky")
    public WeatherResponseDto loadSkyCode() throws IOException, ParseException{
        return openWeatherAPI.findSkyCode();
    }

    @GetMapping("/pop")
    public WeatherResponseDto loadPrecipitation() throws IOException, ParseException{
        return openWeatherAPI.findPrecipitation();
    }

    @GetMapping("/bear")
    public BearResponseDto loadBearInfo() throws IOException, ParseException{
        return openWeatherAPI.findBearInfo();
    }
}
