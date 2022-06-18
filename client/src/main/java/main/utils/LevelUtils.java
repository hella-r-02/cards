package main.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import main.entity.Level;

public class LevelUtils {
    public static boolean isExpired(Level level) {
        LocalDate currentDate = LocalDate.now();
        LocalDate localLevelDate = level.getNext_replay().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        long diff = localLevelDate.until(currentDate, ChronoUnit.DAYS);
        return diff > level.getNum_of_level();
    }
}
