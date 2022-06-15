package main.service;

import java.util.Date;
import java.util.List;

import main.entity.Card;

public interface CardService {
    List<Card> findCardsByLevelId(Long id);

    List<Card> findBYLevelIDAndDate(Long id);

    List<Card> findCardsByFolderId(Long id);

    Card findById(Long id);

    void updateCard(Long id, Long level_id);

    void updateCardByDateAndLevel(Long id, Long level_id, Date date);
}
