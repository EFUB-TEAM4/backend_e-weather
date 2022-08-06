package efub.team4.backend_eweather.domain.calendar.service;

import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.exception.CalendarAlreadyExistsException;
import efub.team4.backend_eweather.domain.calendar.exception.CalendarNotFoundException;
import efub.team4.backend_eweather.domain.calendar.repository.CalendarRepository;
import efub.team4.backend_eweather.domain.user.repository.UserRepository;
import efub.team4.backend_eweather.domain.weather.service.OpenWeatherAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional
    public Calendar save(Calendar calendar) {
        Optional<Calendar> existedCalendar = calendarRepository.findCalendarByForecastDateAndUser_Id(calendar.getForecastDate(), calendar.getUser().getId());
        if (existedCalendar.isPresent()){
            throw new CalendarAlreadyExistsException("Calendar is already exists with date=" + calendar.getForecastDate());
        }
        return calendarRepository.save(calendar);
    }

    @Transactional
    public void delete(UUID id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException("캘린더 게시글을 찾을 수 없습니다."));
        calendarRepository.delete(calendar);
    }

    @Transactional
    public UUID update(UUID id, String description) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException("캘린더 게시글을 찾을 수 없습니다."));

        calendar.update(description);

        return id;
    }

    @Transactional(readOnly = true)
    public Calendar findById(UUID id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException("캘린더 게시글을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Calendar> findAll(Specification<Calendar> spec) {
        return calendarRepository.findAll(spec);
    }

}
