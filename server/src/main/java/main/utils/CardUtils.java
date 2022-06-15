package main.utils;
import java.util.Date;

public class CardUtils {
    public static Date getNextDate(Date cardDate, Date levelDate, Long numOfLevel) {
        if (cardDate.compareTo(levelDate) != 0) {
            return levelDate;
        } else {
            return new Date(levelDate.getTime()+numOfLevel*24*60*60*1000);
        }
    }
}
