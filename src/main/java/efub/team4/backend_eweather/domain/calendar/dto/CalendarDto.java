package efub.team4.backend_eweather.domain.calendar.dto;

import com.sun.istack.NotNull;
import efub.team4.backend_eweather.domain.bear.dto.BearDto;
import efub.team4.backend_eweather.domain.eweather.dto.EweatherDto;
import efub.team4.backend_eweather.domain.icon.dto.IconDto;
import efub.team4.backend_eweather.domain.pty.dto.PtyDto;
import efub.team4.backend_eweather.domain.season.dto.SeasonDto;
import efub.team4.backend_eweather.domain.sky.dto.SkyDto;
import efub.team4.backend_eweather.domain.user.dto.UserResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class CalendarDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalendarCreateRequest {
        @ApiModelProperty(value = "게시글 내용", example = "오늘 날씨 게시글", required = true)
        private String description;

        @ApiModelProperty(value = "현재 시간", example = "1200", required = true)
        private String forecastDate;

        @ApiModelProperty(value = "최저 기온", example = "28", required = true)
        private Integer minTemperature;

        @ApiModelProperty(value = "현재 기온", example = "30", required = true)
        private Integer currentTemperature;

        @ApiModelProperty(value = "최고 기온", example = "32", required = true)
        private Integer maxTemperature;

        @ApiModelProperty(value = "강수 확률", example = "10", required = true)
        private Integer rainfallPercentage;

        @ApiModelProperty(value = "ICON ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID iconId;

        @ApiModelProperty(value = "PTY ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID ptyId;

        @ApiModelProperty(value = "SKY ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID skyId;

        @ApiModelProperty(value = "BEAR ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID bearId;

        @ApiModelProperty(value = "SEASON ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID seasonId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest{
        @ApiModelProperty(value = "게시글 내용", example = "오늘 날씨 게시글", required = true)
        private String description;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteRequest {
        @ApiModelProperty(value = "삭제된 캘린더 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;
        private String message;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @ApiModelProperty(value = "캘린더 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
        private UUID id;

        @ApiModelProperty(value = "현재 시간", example = "1200", required = true)
        private String forecastDate;

        @ApiModelProperty(value = "사용자 정보 Response Dto", required = true)
        private UserResponseDto userResponseDto;

        @ApiModelProperty(value = "게시글 내용", example = "오늘 날씨 게시글", required = true)
        private String description;

        @ApiModelProperty(value = "최저 기온", example = "28", required = true)
        private Integer minTemperature;

        @ApiModelProperty(value = "현재 기온", example = "30", required = true)
        private Integer currentTemperature;

        @ApiModelProperty(value = "최고 기온", example = "32", required = true)
        private Integer maxTemperature;

        private IconDto.IconResponseUrlDto iconResponseUrlDto;
        private SkyDto.SkyResponseDtoWithUrl skyResponseDtoWithUrl;
        private PtyDto.PtyResponseDtoWithUrl ptyResponseDtoWithUrl;
        private BearDto.BearResponseDto bearResponseDto;
        private SeasonDto.SeasonResponseDto seasonResponseDto;

        @ApiModelProperty(value = "캘린더 생성 시각", required = true)
        private LocalDateTime calendarCreatedOn;

        @ApiModelProperty(value = "캘린더 수정 시각", example = "32", required = true)
        private LocalDateTime calendarUpdatedOn;
    }
}
