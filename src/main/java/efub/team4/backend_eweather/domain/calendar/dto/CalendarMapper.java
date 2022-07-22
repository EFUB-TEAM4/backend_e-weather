package efub.team4.backend_eweather.domain.calendar.dto;

import efub.team4.backend_eweather.domain.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalendarMapper {
    private final CalendarRepository calendarRepository;
}
