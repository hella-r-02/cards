package main.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Card;
import main.repository.CardRepository;
import main.service.CardService;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> findCardsByLevelId(Long id) {
        return cardRepository.findByLevelId(id);
    }

    @Override
    public List<Card> findBYLevelIDAndDate(Long id) {
        return cardRepository.findBYLevelIDAndDate(id);
    }

    @Override
    public List<Card> findCardsByFolderId(Long id) {
        return cardRepository.findByFolderId(id);
    }

    @Override
    public Card findById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElse(null);
    }

    //проверка что id сущ
    @Override
    public void updateCard(Long id, Long level_id) {
        cardRepository.updateCard(id, level_id);
    }

    @Override
    public void updateCardByDateAndLevel(Long id, Long level_id, Date date) {
        cardRepository.updateCardByDateAndLevel(id, level_id, date);
    }
}
