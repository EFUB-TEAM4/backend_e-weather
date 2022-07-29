package efub.team4.backend_eweather.domain.weather.service;

import efub.team4.backend_eweather.domain.weather.dto.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OpenWeatherAPI {


    // 커밋 시 서비스 키 지우고 커밋
    private final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private final String serviceKey = "muVi%2BoMzwaX9%2BT2Fs2FhZmttZ8q5%2BfJhBwDbIQ7q1hlgxmyvL7psAHNQx6kzKRXQbtOeYyowUwX2lLBcSj9FbA%3D%3D";
    // 요청 고정갑
    private String pageNo = "1";
    private String numOfRows = "310";
    private String dataType = "JSON";// api 제공시각
    private String nx = "59";
    private String ny = "126"; // nx, ny는 서대문구 신촌동 좌표값

    @Transactional
    public List<OpenWeatherResponseDto> findWeather() throws IOException, ParseException {
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return buildResponse(jsonResult);
    }

    // 예상 날씨
    @Transactional
    public List<ForcastResponseDto> findForcastWeather() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return buildForcastResponse(jsonResult);
    }

    @Transactional
    public CurrentWeatherResponseDto findCalendarWeather() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return buildCurrentData(jsonResult);
    }

    @Transactional
    public CurrentWeatherResponseDto findCurrentWeather() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return buildCurrentData(jsonResult);
    }

    @Transactional
    public WeatherResponseDto findTemperature() throws IOException, ParseException {
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return builldWeatherResponse(jsonResult, "TMP");
    }

    @Transactional
    public WeatherResponseDto findSkyCode() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return builldWeatherResponse(jsonResult, "SKY");
    }

    @Transactional
    public WeatherResponseDto findPrecipitation() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return builldWeatherResponse(jsonResult, "POP");
    }

    public BearResponseDto findBearInfo() throws IOException, ParseException{
        URL url = buildRequestUrl(); // url 생성성
        System.out.println(url);  // url 어떻게 되는지 확인
        String stringResult = httpURLConnect(url);
        JSONArray jsonResult = getItems(stringResult);
        return buildBearResponse(jsonResult);
    }

    public URL buildRequestUrl() throws IOException {
        StringBuilder sb = new StringBuilder(BASE_URL);
        String baseDate = getCurrentDate();
        String baseTime = getBaseTime();
        System.out.println(baseDate + " " + baseTime);
        if(Objects.equals(baseTime, "2300")){
            Date dDate = new Date();
            dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            baseDate = sdf.format(dDate);
        }
        System.out.println(baseDate + " " + baseTime);
        sb.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append(serviceKey);
        sb.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode(pageNo, "UTF-8"));
        sb.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
        sb.append("&").append(URLEncoder.encode("dataType", "UTF-8")).append("=").append(URLEncoder.encode(dataType, "UTF-8")); /* 타입 */
        sb.append("&").append(URLEncoder.encode("base_date", "UTF-8")).append("=").append(URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
        sb.append("&").append(URLEncoder.encode("base_time", "UTF-8")).append("=").append(URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        sb.append("&").append(URLEncoder.encode("nx", "UTF-8")).append("=").append(URLEncoder.encode(nx, "UTF-8")); // 경도
        sb.append("&").append(URLEncoder.encode("ny", "UTF-8")).append("=").append(URLEncoder.encode(ny, "UTF-8")); // 위도

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

    public JSONArray getItems(String stringResult) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stringResult);
        JSONObject parseResponse = (JSONObject) jsonObject.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItem = (JSONArray) parseItems.get("item");

        return parseItem;
    }

    public List<ForcastResponseDto> buildForcastResponse(JSONArray parseItem){
        List<ForcastResponseDto> dtoList = new ArrayList<>();
        JSONObject obj;

        String fcstDate ="";
        String fcstTime ="";
        String sky ="";
        String pty = "";
        String tmp = "";

        Integer idx = 0;
        while(dtoList.size() < 22){
            obj = (JSONObject) parseItem.get(idx);
            fcstDate = (String) obj.get("fcstDate");
            fcstTime = (String) obj.get("fcstTime");
            tmp = (String) obj.get("fcstValue");

            obj = (JSONObject) parseItem.get(idx+5);
            sky = (String) obj.get("fcstValue");

            obj = (JSONObject) parseItem.get(idx+6);
            pty = (String) obj.get("fcstValue");

            ForcastResponseDto dto = ForcastResponseDto.builder()
                    .baseDate(getCurrentDate())
                    .baseTime(getBaseTime())
                    .fcstDate(fcstDate)
                    .fcstTime(fcstTime)
                    .tmp(tmp)
                    .sky(sky)
                    .pty(pty)
                    .build();

            dtoList.add(dto);
            idx += 12;
            obj = (JSONObject) parseItem.get(idx);
            String s = (String) obj.get("category");
            if((Objects.equals(s, "TMN")) || (Objects.equals(s, "TMX"))){
                idx++;
            }
        }

        return dtoList;
    }


    private CurrentWeatherResponseDto buildCurrentData(JSONArray jsonResult) {
        // 현재 시간 받아서 예상 시간 조회
        String fcstTime = getFcstTime();
        if(fcstTime.length() == 3){
            fcstTime = "0" + fcstTime;
        }
        if(fcstTime.equals("2400")){
            fcstTime = "0000";
        }
        String baseDate = getCurrentDate();
        String tmp = "";
        String tmx = "";
        String tmn = "";
        String sky = "";
        String pop = "";
        String pty = "";

        // jsonResult를 조회하며 필요한 데이터 받기
        for(int i = 0; i < jsonResult.size(); i++){
            JSONObject obj = (JSONObject) jsonResult.get(i);

            String TimeTemp = (String) obj.get("fcstTime");
            String CategoryTemp = (String) obj.get("category");

            if(CategoryTemp.equals("TMN")){
                tmn = (String) obj.get("fcstValue");
            }
            else if(CategoryTemp.equals("TMX")){
                tmx = (String) obj.get("fcstValue");
            }
            else if(!fcstTime.equals(TimeTemp)){
                continue;
            }
            else{
                if(CategoryTemp.equals("TMP")){
                    tmp = (String) obj.get("fcstValue");
                }
                if(CategoryTemp.equals("SKY")){
                    sky = (String) obj.get("fcstValue");
                }
                if(CategoryTemp.equals("POP")){
                    pop = (String) obj.get("fcstValue");
                }
                if(CategoryTemp.equals("PTY")){
                    pty = (String) obj.get("fcstValue");
                }
            }

            /*
                        if((tmn != null) && (tmx != null) && (tmp != null) && (sky != null) && (pop != null)){
                break;
            }
             */
        }

        // dto로 만들어서 반환
        CurrentWeatherResponseDto responseDto = CurrentWeatherResponseDto.builder()
                .baseDate(baseDate)
                .baseTime(getBaseTime())
                .fcstDate(baseDate)
                .fcstTime(fcstTime)
                .tmp(tmp)
                .tmx(tmx)
                .tmn(tmn)
                .sky(sky)
                .pop(pop)
                .pty(pty)
                .build();

        return responseDto;
    }

    public WeatherResponseDto builldWeatherResponse(JSONArray jsonResult, String category) {
        // 현재 시간 받아서 예상 시간 조회
        String fcstTime = getFcstTime();
        if (fcstTime.length() == 3) {
            fcstTime = "0" + fcstTime;
        }
        if(fcstTime.equals("2400")){
            fcstTime = "0000";
        }
        String baseDate = getCurrentDate();
        String value = "";

        // jsonResult를 조회하며 필요한 데이터 받기
        for (int i = 0; i < jsonResult.size(); i++) {
            JSONObject obj = (JSONObject) jsonResult.get(i);

            String TimeTemp = (String) obj.get("fcstTime");
            String CategoryTemp = (String) obj.get("category");

            if (!fcstTime.equals(TimeTemp)) {
                continue;
            } else {
                if (CategoryTemp.equals(category)) {
                    value = (String) obj.get("fcstValue");
                }
            }
        }
        WeatherResponseDto responseDto = WeatherResponseDto
                .builder()
                .baseDate(baseDate)
                .baseTime(getBaseTime())
                .fcstDate(baseDate)
                .fcstTime(fcstTime)
                .category(category)
                .value(value)
                .build();

        return responseDto;
    }

    public List<OpenWeatherResponseDto> buildResponse(JSONArray parse_item){
        List<OpenWeatherResponseDto> dataList = new ArrayList<OpenWeatherResponseDto>();

        JSONObject obj;

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

    private BearResponseDto buildBearResponse(JSONArray jsonResult) {

        String fcstTime = getFcstTime();
        if (fcstTime.length() == 3) {
            fcstTime = "0" + fcstTime;
        }
        if(fcstTime.equals("2400")){
            fcstTime = "0000";
        }
        String baseDate = getCurrentDate();

        String tmp = "";
        String tmx = "";
        String tmn = "";
        String sky = "";
        String pop = "";
        String pty = "";

        // jsonResult를 조회하며 필요한 데이터 받기
        for (int i = 0; i < jsonResult.size(); i++) {
            JSONObject obj = (JSONObject) jsonResult.get(i);

            String TimeTemp = (String) obj.get("fcstTime");
            String CategoryTemp = (String) obj.get("category");

            if (!fcstTime.equals(TimeTemp)) {
                continue;
            } else {
                if (CategoryTemp.equals("TMP")) {
                    tmp = (String) obj.get("fcstValue");
                }
                if (CategoryTemp.equals("SKY")) {
                    sky = (String) obj.get("fcstValue");
                }
                if (CategoryTemp.equals("PTY")) {
                    pty = (String) obj.get("fcstValue");
                }
            }
        }

        BearResponseDto responseDto = BearResponseDto.builder()
                .baseDate(baseDate)
                .baseTime(getBaseTime())
                .fcstDate(baseDate)
                .fcstTime(fcstTime)
                .tmp(tmp)
                .sky(sky)
                .pty(pty)
                .build();

        return responseDto;
    }

    public String getCurrentDate(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        return simpleDateFormat.format(currentDate);
    }

    public String getFcstTime(){
        DateFormat simpleDateFormat = new SimpleDateFormat("k");
        Date currentTime = new Date();
        return simpleDateFormat.format(currentTime) + "00";
    }


    public String getBaseTime(){
        DateFormat simpleDateFormat = new SimpleDateFormat("k");
        Date currentHour = new Date();
        String ApiTime = simpleDateFormat.format(currentHour);
        System.out.println(ApiTime);
        return switch (ApiTime) {
            case "24", "1", "2" -> "2300";
            case "3", "4", "5" -> "0200";
            case "6", "7", "8" -> "0500";
            case "9", "10", "11" -> "0800";
            case "12", "13", "14" -> "1100";
            case "15", "16", "17" -> "1400";
            case "18", "19", "20" -> "1700";
            case "21", "22", "23" -> "2000";
            default -> "";
        };
    }


    public String getDay(){
        DateFormat simpleDateFormat = new SimpleDateFormat("k");
        Date currentTime = new Date();
        Integer time = Integer.parseInt(simpleDateFormat.format(currentTime));
        if(time > 6 && time < 18 ){
            return "day";
        }
        else{
            return "night";
        }
    }

    public Integer getMonth(){
        DateFormat simpleDateFormat = new SimpleDateFormat("M");
        Date currentTime = new Date();
        return Integer.parseInt(simpleDateFormat.format(currentTime));
    }

}