package efub.team4.backend_eweather.domain.calendar.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarSearchCriteria {
    private UUID userId;
    private String forecastDate;
}
