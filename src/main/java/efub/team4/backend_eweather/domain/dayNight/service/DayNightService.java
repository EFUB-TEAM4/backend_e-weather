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
        dayNightRepository.findByTimeName(dayNight.getTimeName())
                .ifPresent((existedDayNight) -> {
                    throw new DayNightAlreadyExistsException("DayNight already exists with specified DayNight name");
                });
        return dayNightRepository.save(dayNight);
    }

    @Transactional(readOnly = true)
    public DayNight findByTime(LocalTime time) {
        Optional<DayNight> dayOptional = dayNightRepository.findByTimeName("day");
        Optional<DayNight> nightOptional = dayNightRepository.findByTimeName("night");

        dayOptional.ifPresent((existedDay) -> {
            throw new DayNightNotFoundException("DayNight not found with day");
        });

        nightOptional.ifPresent((existedNight) -> {
            throw new DayNightNotFoundException("DayNight not found with night");
        });

        LocalTime dayTime = dayOptional.get().getTime();
        LocalTime nightTime = nightOptional.get().getTime();
        if ((dayTime.isAfter(time) || dayTime.equals(time)) && nightTime.isBefore(time)) {
            return dayOptional.get();
        } else {
            return nightOptional.get();
        }
    }
}
