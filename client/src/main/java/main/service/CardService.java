package main.service;

import main.entity.Card;

public interface CardService {
    Card[] getCardsByFolderId(Long id);
}
