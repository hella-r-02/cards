package main.service;

import java.util.List;

import main.entity.Card;

public interface CardService {
    List<Card> findCardsByLevelId(Long id);
    List<Card> findCardsByFolderId(Long id);
}
