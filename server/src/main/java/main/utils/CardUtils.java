package main.utils;

import java.util.Date;

import main.entity.Card;
import main.entity.Level;

public class CardUtils {
    public static Date getNextDate(Date cardDate, Date levelDate, Long numOfLevel) {
        Date currentDate = new Date();
        if (cardDate.compareTo(levelDate) != 0 && levelDate.compareTo(currentDate) != 0) {
            return levelDate;
        } else {
            return new Date(levelDate.getTime() + numOfLevel * 24 * 60 * 60 * 1000);
        }
    }

    public static boolean isReadyToRepeat(Level level, Card card) {
        Date currentDate = new Date();
        return (card.getNext_replay().compareTo(level.getNext_replay()) <= 0 ||
                (card.getNext_replay().compareTo(level.getNext_replay()) > 0 &&
                        card.getNext_replay().compareTo(currentDate) < 0));

    }
}
