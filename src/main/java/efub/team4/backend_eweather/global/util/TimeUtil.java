package efub.team4.backend_eweather.global.util;

import lombok.RequiredArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
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

    public Integer getHour(){
        DateFormat simpleDateFormat = new SimpleDateFormat("k");
        Date currentTime = new Date();
        return Integer.parseInt(simpleDateFormat.format(currentTime));
    }

    public Integer getMonth(){
        DateFormat simpleDateFormat = new SimpleDateFormat("M");
        Date currentTime = new Date();
        return Integer.parseInt(simpleDateFormat.format(currentTime));
    }

}
