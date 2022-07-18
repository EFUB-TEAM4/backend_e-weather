package efub.team4.backend_eweather.domain.weather.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class OpenWeatherResponseDto {
    /** 발표일자 */
    private String baseDate;

    /** 발표시각 */
    private String baseTime;

    /** 예보일자 */
    private String fcstDate;

    /** 예보시각 */
    private String fcstTime;

    /** 자료구분문자 */
    private String category;

    /** 예보 값 */
    private String fcstValue;

    /** 예보지점 X 좌표 */
    private int nx;

    /** 예보지점 Y 좌표 */
    private int ny;

    /**
     * 코드값 정보
     */

    /* 일 단 빼... 돌아가고 나서 이넘으로 개션하기
    @Getter
    public enum CategoryType {
        POP("강수확률", "강수확률"),
        PTY("강수형태", "코드값"),
        PCP("1시간 강수량", "범주"),
        REH("습도", "%"),
        SNO("1시간 신적설", "범주"),
        SKY("하늘상태", "코드값"),
        TMP("1시간 기온", "℃"),
        TMN("일 최저기온", "℃"),
        TMX("일 최고기온", "℃"),
        UUU("풍속(동서성분)", "m/s"),
        VVV("풍속(남북성분)", "m/s"),
        WAV("파고", "M"),
        VEC("풍향", "deg"),
        WSD("풍속", "m/s");

        private String name;
        private String unit;

        private CategoryType(String name, String unit) {
            this.name = name;
            this.unit = unit;
        }
    }
    */

    @Builder
    public OpenWeatherResponseDto(String baseDate, String baseTime, String fcstDate, String fcstTime, String category, String fcstValue, int nx, int ny) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.category = category;
        this.fcstValue = fcstValue;
        this.nx = nx;
        this.ny = ny;
    }
}
