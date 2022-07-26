package efub.team4.backend_eweather.domain.calendar.controller;


import efub.team4.backend_eweather.domain.calendar.dto.CalendarDto;
import efub.team4.backend_eweather.domain.calendar.dto.CalendarMapper;
import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import efub.team4.backend_eweather.domain.calendar.service.CalendarService;
import efub.team4.backend_eweather.domain.calendar.specification.CalendarSearchCriteria;
import efub.team4.backend_eweather.domain.calendar.specification.CalendarSpecification;
import efub.team4.backend_eweather.domain.user.dto.SessionUser;
import efub.team4.backend_eweather.global.config.auth.LoginUser;
import efub.team4.backend_eweather.global.dto.DeletedEntityIdResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/calendars")
@Api(tags = {"Calendar API"}, description = "캘린더 생성, 수정, 조회, 삭제")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    @PostMapping
    @ApiOperation(value = "캘린더 생성", notes = "캘린더를 생성한다.")
    public ResponseEntity<CalendarDto.Response> createCalendar(
            @LoginUser SessionUser user,
            @ApiParam(value = "캘린더 생성 DTO") @RequestBody CalendarDto.CalendarCreateRequest requestDto) {


        Calendar entity = calendarService.save(calendarMapper.createRequestDtoToEntity(user.getId(), requestDto));
        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(CalendarController.class)
                                .slash(entity.getId())
                                .toUri())
                .body(calendarMapper.CalendarResponse(entity));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "캘린더 수정", notes = "캘린더를 수정한다.")
    public ResponseEntity<CalendarDto.Response> updateCalendar(@ApiParam(value = "수정할 캘린더 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id,
                                                               @ApiParam(value = "캘린더 수정 DTO") @RequestBody CalendarDto.UpdateRequest updateRequest) {
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
            @ApiParam(value = "현재 사용자", required = true) @LoginUser SessionUser user,
            @ApiParam(value = "캘린더 ID", required = true, example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) {

        Calendar calendar = calendarService.findById(id);


        if (!user.getId().equals(calendar.getUser().getId())) {
            throw new InvalidParameterException("User Forbidden Exception with id = " + user.getId());
        }

        calendarService.delete(id);

        return ResponseEntity
                .ok()
                .body(new DeletedEntityIdResponseDto(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "캘린더 상세 조회", notes = "캘린더를 조회한다.")
    public ResponseEntity<CalendarDto.Response> getCalendar(
            @ApiParam(value = "캘린더 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                    required = true) @PathVariable UUID id) {
        Calendar calendar = calendarService.findById(id);
        return ResponseEntity
                .ok()
                .body(calendarMapper.CalendarResponse(calendar));
    }

    @GetMapping
    @ApiOperation(value = "캘린더 조건 조회", notes = "사용자와 날짜에 따라 캘린더를 조회한다.")
    public ResponseEntity<List<CalendarDto.Response>> getCalendarList(
            @ApiParam(value = "사용자 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @RequestParam(required = false) UUID userId,
            @ApiParam(value = "검색 날짜 예제", example = "20220725") @RequestParam(required = false) String forecastDate
    ) {

        CalendarSpecification spec = new CalendarSpecification(
                CalendarSearchCriteria.builder()
                        .userId(userId)
                        .forecastDate(forecastDate)
                        .build());
        List<Calendar> response = calendarService.findAll(spec);

        return ResponseEntity
                .ok()
                .body(response.stream().map(calendarMapper::CalendarResponse).collect(Collectors.toList()));
    }

}
