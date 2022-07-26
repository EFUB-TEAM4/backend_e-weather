package efub.team4.backend_eweather.domain.season.dto;


import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonMapper {
    private final SeasonRepository seasonRepository;

    public Season createRequestDtoToEntity(SeasonDto.SeasonCreateDto requestDto){
        return Season.builder()
                .startMonth(requestDto.getStartMonth())
                .endMonth(requestDto.getEndMonth())
                .seasonName(requestDto.getSeasonName())
                .seasonsBackGroundFileUrl(requestDto.getSeasonBackGroundFileUrl())
                .build();
    }

    public SeasonDto.SeasonResponseDto fromEntity(Season entity){
        return SeasonDto.SeasonResponseDto.builder()
                .id(entity.getId())
                .seasonName(entity.getSeasonName())
                .seasonBackGroundFileUrl(entity.getSeasonBackGroundFileUrl())
                .build();
    }
}
