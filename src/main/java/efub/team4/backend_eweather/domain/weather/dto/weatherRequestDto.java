package efub.team4.backend_eweather.domain.weather.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class weatherRequestDto {

    private String baseDate;
    private String baseTime;

    @Builder
    public weatherRequestDto(String baseDate, String baseTime) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
    }
}
