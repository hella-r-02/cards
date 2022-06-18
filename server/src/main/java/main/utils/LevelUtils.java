package main.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import main.entity.Level;

public class LevelUtils {
    public static Date getNextDate(Level level) {
        LocalDate currentDate = LocalDate.now();
        LocalDate localLevelDate = level.getNext_replay().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        long diff = localLevelDate.until(currentDate, ChronoUnit.DAYS);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(level.getNext_replay());
        if (diff <= level.getNum_of_level()) {
            calendar.add(Calendar.DATE, Math.toIntExact(level.getNum_of_level()));
        } else {
            System.out.println(diff);
            calendar.add(Calendar.DATE, Math.toIntExact(diff / level.getNum_of_level() + level.getNum_of_level()));
        }
        return calendar.getTime();
    }
}
