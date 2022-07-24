package efub.team4.backend_eweather.domain.season.service;

import efub.team4.backend_eweather.domain.season.entity.Season;
import efub.team4.backend_eweather.domain.season.exception.SeasonAlreadyExistsException;
import efub.team4.backend_eweather.domain.season.exception.SeasonNotFoundException;
import efub.team4.backend_eweather.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Transactional
    public Season save(Season season){
        seasonRepository.findById(season.getId())
                .ifPresent((existedSeason)->{
                    throw new SeasonAlreadyExistsException("Seasons already exist with specified Seasons name");
                });
        return seasonRepository.save(season);
    }

    @Transactional(readOnly = true)
    public Season findByMonth(Integer month){
        Season season = seasonRepository.findByMonth(month).orElseThrow(()-> new SeasonNotFoundException("Season not found"));
        return season;
    }
}
