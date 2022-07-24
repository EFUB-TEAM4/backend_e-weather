package efub.team4.backend_eweather.domain.calendar.controller;


import efub.team4.backend_eweather.domain.calendar.dto.CalendarDto;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarMapper;
import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.service.CalendarService;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import efub.team4.backend_eweather.global.dto.DeletedEntityIdResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.InvalidParameterException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/calendars")
@Api(tags = {"Calendar API"})
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    @PostMapping
    @ApiOperation(value = "캘린더 생성", notes = "캘린더를 생성한다.")
    public ResponseEntity<CalendarDto.Response> createCalendar(@RequestBody CalendarDto.CreateRequest requestDto) {
        Calendar entity = calendarMapper.createRequestDtoToEntity(requestDto);
        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(CalendarController.class)
                                .slash(entity.getId())
                                .toUri())
                .body(calendarMapper.CalendarResponse(entity));
    }
 /*

    @PostMapping
    @ApiOperation(value = "캘린더 생성", notes = "캘린더를 생성한다.")
    public ResponseEntity<CalendarDto.Response> createCalendar(@LoginUser SessionUser user, @RequestBody CalendarDto.CreateRequest requestDto) {
        Calendar entity = calendarMapper.createRequestDtoToEntity(user.getId(), requestDto);
        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(CalendarController.class)
                                .slash(entity.getId())
                                .toUri())
                .body(calendarMapper.CalendarResponse(entity));
    }*/

    @PutMapping("/{id}")
    @ApiOperation(value = "캘린더 수정", notes = "캘린더를 수정한다.")
    public ResponseEntity<CalendarDto.Response> updateCalendar(@PathVariable UUID id, @RequestBody CalendarDto.UpdateRequest updateRequest) {
        UUID calendarId = calendarService.update(id, updateRequest.getDescription());
        Calendar calendar = calendarService.findById(calendarId);

        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(CalendarController.class)
                                .slash(calendar.getId())
                                .toUri())
                .body(calendarMapper.CalendarResponse(calendar));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "캘린더 삭제", notes = "캘린더를 삭제한다.")
    public ResponseEntity<DeletedEntityIdResponseDto> deleteCalendar(
            @ApiParam(value = "캘린더 ID", required = true) @PathVariable UUID id) {

        Calendar calendar = calendarService.findById(id);

        /*
        if (!user.getId().equals(calendar.getUser().getId())) {
            throw new InvalidParameterException("User Forbidden Exception with id = " + user.getId());
        }*/

        calendarService.delete(id);

        return ResponseEntity
                .ok()
                .body(new DeletedEntityIdResponseDto(id));
    }

}
