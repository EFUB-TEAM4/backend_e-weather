package efub.team4.backend_eweather.domain.eweather.entity;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Eweather {

    private String forecastDate;
    private String forecastTime;
    private Integer minTemperature;
    private Integer currentTemperature;
    private Integer maxTemperature;
    private Integer rainfallPercentage;
    private Sky sky;
    private Pty pty;
    private Icon icon;
    private Bear bear;
    private Season season;

    @Builder
    public Eweather(String forecastDate, String forecastTime, Integer minTemperature, Integer currentTemperature,
                    Integer maxTemperature, Integer rainfallPercentage, Sky sky,
                    Pty pty, Icon icon, Bear bear, Season season) {
        this.forecastDate = forecastDate;
        this.forecastTime = forecastTime;
        this.minTemperature = minTemperature;
        this.currentTemperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.rainfallPercentage = rainfallPercentage;
        this.sky = sky;
        this.pty = pty;
        this.icon = icon;
        this.bear = bear;
        this.season = season;
    }
}
