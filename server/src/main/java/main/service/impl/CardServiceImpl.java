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
import main.service.CardService;
import main.utils.CardUtils;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    LevelRepository levelRepository;

    @Override
    public List<Card> findCardsByLevelId(Long id) {
        return cardRepository.findByLevelId(id);
    }

    @Override
    public List<Card> findByLevelIdAndDate(Long id) {
        Optional<Level> levelOptional = levelRepository.findById(id);
        if (levelOptional.isPresent()) {
            Level level = levelOptional.get();
            List<Card> cards = level.getCards();
            cards.removeIf(c -> !CardUtils.isReadyToRepeat(level, c));
            return cards;
        }
        return null;
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

    @Override
    public void updateCard(Long id, Long level_id) {
        cardRepository.updateCard(id, level_id);
    }

    @Override
    public void updateCardByDateAndLevel(Long id, Long level_id, Date date) {
        cardRepository.updateCardByDateAndLevel(id, level_id, date);
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public void updateCardsByLevel(Long level_id, Long new_level_id) {
        cardRepository.updateCardsByLevel(level_id, new_level_id);
    }

    @Override
    public void addNewLevelTextQuestionTextAnswer(Long levelId, String question, String answer, Date nextReplay) {
        cardRepository.addNewLevelTextQuestionTextAnswer(levelId, question, answer, nextReplay);
    }

    @Override
    public void addNewLevelTextQuestionImgAnswer(Long levelId, String question, byte[] answerImage, Date nextReplay) {
        cardRepository.addNewLevelTextQuestionImgAnswer(levelId, question, answerImage, nextReplay);
    }

    @Override
    public void addNewLevelImgQuestionTextAnswer(Long levelId, byte[] questionImage, String answer, Date nextReplay) {
        cardRepository.addNewLevelImgQuestionTextAnswer(levelId, questionImage, answer, nextReplay);
    }

    @Override
    public void addNewLevelImgQuestionImgAnswer(Long levelId, byte[] questionImage, byte[] answerImage, Date nextReplay) {
        cardRepository.addNewLevelImgQuestionImgAnswer(levelId, questionImage, answerImage, nextReplay);
    }

    @Override
    public void updateCardByQuestion(Long id, String question) {
        cardRepository.updateCardByQuestion(id, question);
    }

    @Override
    public void updateCardByQuestionImage(Long id, byte[] questionImage) {
        cardRepository.updateCardByQuestionImage(id, questionImage);
    }

    @Override
    public void updateCardByAnswer(Long id, String answer) {
        cardRepository.updateCardByAnswer(id, answer);
    }

    @Override
    public void updateCardByAnswerImage(Long id, byte[] answerImage) {
        cardRepository.updateCardByAnswerImage(id, answerImage);
    }

    @Override
    public void updateCardByQuestionAndAnswer(Long id, String question, String answer) {
        cardRepository.updateCardByQuestion(id, question);
        cardRepository.updateCardByAnswer(id, answer);
    }

    @Override
    public void updateCardByQuestionAndAnswerImage(Long id, String question, byte[] answerImage) {
        cardRepository.updateCardByQuestion(id, question);
        cardRepository.updateCardByAnswerImage(id, answerImage);
    }

    @Override
    public void updateCardByQuestionImageAndAnswer(Long id, byte[] questionImage, String answer) {
        cardRepository.updateCardByQuestionImage(id, questionImage);
        cardRepository.updateCardByAnswer(id, answer);
    }

    @Override
    public void updateCardByQuestionImageAndAnswerImage(Long id, byte[] questionImage, byte[] answerImage) {
        cardRepository.updateCardByQuestionImage(id, questionImage);
        cardRepository.updateCardByAnswerImage(id, answerImage);
    }
}
