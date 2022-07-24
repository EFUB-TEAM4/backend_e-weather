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

    public String getCurrentTime(){
        DateFormat simpleDateFormat = new SimpleDateFormat("k");
        Date currentTime = new Date();
        return simpleDateFormat.format(currentTime) + "00";
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
