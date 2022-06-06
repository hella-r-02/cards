package main.service.impl;

import java.util.List;

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
    public List<Card> findByFolderId(Long id) {
        return cardRepository.findByFolderId(id);
    }
}
