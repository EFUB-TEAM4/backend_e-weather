package efub.team4.backend_eweather.domain.weather.service;

import efub.team4.backend_eweather.domain.weather.dto.OpenWeatherResponseDto;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OpenWeatherAPI {

    // 커밋 시 서비스 키 지우고 커밋
    private final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private final String serviceKey = "muVi%2BoMzwaX9%2BT2Fs2FhZmttZ8q5%2BfJhBwDbIQ7q1hlgxmyvL7psAHNQx6kzKRXQbtOeYyowUwX2lLBcSj9FbA%3D%3D";

    // 요청 고정갑
    private String pageNo = "1";
    private String numOfRows = "225";
    private String data_type = "JSON";
    private String baseTime = "0500"; // api 제공시각
    private String nx = "59";
    private String ny = "126"; // nx, ny는 서대문구 신촌동 좌표값


    public List<OpenWeatherResponseDto> findWeather() throws IOException, ParseException {
        String baseDate = getCurrentDate();

        // url 생성성
       URL url = buildRequestUrl(baseDate);

        // url 어떻게 되는지 확인
        System.out.println(url);

        // httpURLConnection 사용하여 String 결과값 방아오기
        String stringResult = httpURLConnect(url);

        // stringResult -> json parsing
        JSONArray jsonResult = jsonParsing(stringResult);

        return buildResponse(jsonResult);

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
        sb.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append(serviceKey);
        sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
        sb.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */
        sb.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
        sb.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        sb.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
        sb.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); // 위도

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

    public JSONArray jsonParsing(String stringResult) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stringResult);
        JSONObject parse_response = (JSONObject) jsonObject.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");

        return parse_item;
    }

    public List<OpenWeatherResponseDto> buildResponse(JSONArray parse_item){
        List<OpenWeatherResponseDto> dataList = new ArrayList<OpenWeatherResponseDto>();

        JSONObject obj; // 기준 날짜와 기준시간을 VillageWeather 객체에 저장합니다.


        for(int i = 0; i < parse_item.size(); i++){
            obj = (JSONObject) parse_item.get(i);

            String baseDate = (String) obj.get("baseDate");
            String baseTime = (String) obj.get("baseTime");
            String category = (String) obj.get("category");
            String fcstDate = (String) obj.get("fcstDate");
            String fcstTime = (String) obj.get("fcstTime");
            String fcstValue = (String) obj.get("fcstValue");
            Long nx = (Long) obj.get("nx");
            Long ny = (Long) obj.get("ny");


            OpenWeatherResponseDto weatherResponseDto = new OpenWeatherResponseDto(
                baseDate, baseTime, fcstDate, fcstTime, category, fcstValue, nx, ny
            );

            dataList.add(weatherResponseDto);

        }

        return dataList;
    }


}
