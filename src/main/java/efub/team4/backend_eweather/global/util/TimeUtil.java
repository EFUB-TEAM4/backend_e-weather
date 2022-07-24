package efub.team4.backend_eweather.global.util;

import lombok.RequiredArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {
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
        Integer ApiTime = Integer.parseInt(String.valueOf(currentHour));
        String baseTime = switch (ApiTime) {
            case 0, 1, 2 -> "2300";
            case 3, 4, 5 -> "0200";
            case 6, 7, 8 -> "0500";
            case 9, 10, 11 -> "0800";
            case 12, 13, 14 -> "1100";
            case 15, 16, 17 -> "1400";
            case 18, 19, 20 -> "1700";
            case 21, 22, 23 -> "2000";
            default -> "";
        };
        return baseTime;
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
