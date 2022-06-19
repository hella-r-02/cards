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
        return localLevelDate.until(currentDate, ChronoUnit.DAYS) > 0;
    }
}
