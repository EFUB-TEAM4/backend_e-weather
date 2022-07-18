package efub.team4.backend_eweather.domain.weather.service;

import efub.team4.backend_eweather.domain.weather.dto.OpenWeatherResponseDto;
import org.springframework.http.RequestEntity;
import org.json.simple.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class OpenWeatherAPI {

    // 커밋 시 서비스 키 지우고 커밋
    private final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";
    private final String serviceKey = "muVi%2BoMzwaX9%2BT2Fs2FhZmttZ8q5%2BfJhBwDbIQ7q1hlgxmyvL7psAHNQx6kzKRXQbtOeYyowUwX2lLBcSj9FbA%3D%3D";

    // 요청 고정갑
    private String pageNo = "1";
    private String numOfRows = "225";
    private String data_type = "JSON";
    private String baseTime = "0500"; // api 제공시각
    private String nx = "59";
    private String ny = "126"; // nx, ny는 서대문구 신촌동 좌표값


    public List<OpenWeatherResponseDto> findWeather() throws IOException {
        String baseDate = getCurrentDate();
        URL url = buildRequestUrl(baseDate);

        // url 어떻게 되는지 확인
        System.out.println(url);

        // httpURLConnection 사용하여 String 결과값 방아오기
        String stringResult = httpURLConnect(url);

        // stringResult -> json parsing
    }

    // 현재 요일 반환 함수
    public String getCurrentDate(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        return simpleDateFormat.format(currentDate);
    }

    public URL buildRequestUrl(String baseDate) throws IOException {
        StringBuilder sb = new StringBuilder(BASE_URL);
        // append로만 구성되게 바꾸고는 싶은데 공식문서 참고코드에 이렇게 나와있으니 잠시 대기
        sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
        sb.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */
        sb.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
        sb.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        sb.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
        sb.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")+"&"); // 위도

        return new URL(sb.toString());
    }

    // 우선 문자열로 받아오는 부분
    public String httpURLConnect(URL url) throws IOException{
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else{
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while((line = rd.readLine()) != null){
            sb.append(line);
        }

        rd.close();;
        conn.disconnect();
        return sb.toString();
    }

    public void jsonParsing() throws JsonParseException{

        JsonParser jsonParser = new JsonParser();

    }

}
