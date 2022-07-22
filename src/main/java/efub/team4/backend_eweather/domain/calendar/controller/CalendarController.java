package efub.team4.backend_eweather.domain.calendar.controller;

import efub.team4.backend_eweather.domain.calendar.dto.CalendarDto;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarMapper;
import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/calendars")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

}
