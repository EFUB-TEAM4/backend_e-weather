package efub.team4.backend_eweather.domain.dayNight.service;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightAlreadyExistsException;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DayNightService {
    private final DayNightRepository dayNightRepository;

    @Transactional
    public DayNight save(DayNight dayNight) {
        dayNightRepository.findById(dayNight.getId())
                .ifPresent((existedDayNight) -> {
                    throw new DayNightAlreadyExistsException("DayNight already exists with specified DayNight name");
                });
        return dayNightRepository.save(dayNight);
    }

    @Transactional(readOnly = true)
    public DayNight findByTime(String time) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(time).orElseThrow(() -> new DayNightNotFoundException("DayNight not found"));
        return dayNight;
    }
}
