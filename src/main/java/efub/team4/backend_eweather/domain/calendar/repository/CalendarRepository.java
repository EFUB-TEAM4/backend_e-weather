package efub.team4.backend_eweather.domain.calendar.repository;

import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface CalendarRepository extends JpaRepository<Calendar, UUID>, JpaSpecificationExecutor<Calendar> {
    Optional<Calendar> findCalendarByForecastDate(String foreCastDate);
    Optional<Calendar> findCalendarByForecastDateAndUser_Id(String forCastDate, UUID userId);
}
