package efub.team4.backend_eweather.domain.sky.service;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyAlreadyExistsException;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkyService {
    private final SkyRepository skyRepository;
    private final DayNightRepository dayNightRepository;

    @Transactional
    public Sky save(Sky sky) {
        skyRepository.findBySkyName(sky.getSkyName())
                .ifPresent((existedSky) -> {
                    throw new SkyAlreadyExistsException("Sky already exists with specified sky name");
                });
        return skyRepository.save(sky);
    }

    @Transactional(readOnly = true)
    public Sky findBySkyCodeAndTime(Integer skyCode, String time) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(time)
                .orElseThrow(() -> new DayNightNotFoundException("DayNight Not Found with time = " + time));

        return skyRepository.findSkyBySkyCodeAndDayNight_Id(skyCode, dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky Not found with skyCode and time"));
    }

}
