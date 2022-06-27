package main.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Card;
import main.entity.Level;
import main.repository.CardRepository;
import main.repository.LevelRepository;
import main.service.LevelService;
import main.utils.CardUtils;
import main.utils.LevelUtils;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Level> findLevelsByFolderId(Long id) {
        return levelRepository.findByFolderId(id);
    }

    @Override
    public Level findNextLevel(Long id, Long num_of_level) {
        Optional<Level> level = levelRepository.findNextLevelByFolderId(id, num_of_level);
        return level.orElse(null);
    }

    @Override
    public Level findPrevLevel(Long id, Long num_of_level) {
        Optional<Level> level = levelRepository.findPrevLevelByFolderId(id, num_of_level);
        return level.orElse(null);
    }

    @Override
    public Level getLevelWithCardsThatReadyToRepeat(Long id) {
        Optional<Level> levelOptional = levelRepository.findById(id);
        if (levelOptional.isPresent()) {
            Level level = levelOptional.get();
            List<Card> cards = level.getCards();
            cards.removeIf(c -> !CardUtils.isReadyToRepeat(level, c));
            level.setCards(cards);
            return level;
        }
        return null;
    }

    @Override
    public void updateLevel(Long id) {
        Optional<Level> levelOptional = levelRepository.findById(id);
        if (levelOptional.isPresent()) {
            Level level = levelOptional.get();
            Date nextDate = LevelUtils.getNextDate(level);
            levelRepository.updateLevel(id, nextDate);
        }
    }

    @Override
    public List<Level> getAllLevels() {
        return (List<Level>) levelRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        levelRepository.deleteById(id);
    }

    @Override
    public void addNewLevel(Long folderId, Date date, int numOfLevels) {
        levelRepository.addNewLevel(folderId, date, numOfLevels);
    }

    @Override
    public Level findLevelByFolderIdAndNumOFLevel(Long folderId, int numOfLevels) {
        Optional<Level> levelOptional = levelRepository.findLevelByFolderIdAndNumOFLevel(folderId, numOfLevels);
        return levelOptional.orElse(null);
    }

    @Override
    public Level findById(Long id) {
        Optional<Level> levelOptional = levelRepository.findById(id);
        return levelOptional.orElse(null);
    }

    @Override
    public List<Level> getAllLevelsWithCards() {
        List<Level> levelList = (List<Level>) levelRepository.findAll();
        levelList.removeIf(level -> level.getCards().isEmpty());
        return levelList;
    }
}
