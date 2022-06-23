package main.service;

import java.util.Date;
import java.util.List;

import main.entity.Card;

public interface CardService {
    List<Card> findCardsByLevelId(Long id);

    List<Card> findByLevelIdAndDate(Long id);

    List<Card> findCardsByFolderId(Long id);

    Card findById(Long id);

    void updateCard(Long id, Long level_id);

    void updateCardByDateAndLevel(Long id, Long level_id, Date date);

    void deleteById(Long id);

    void updateCardsByLevel(Long level_id, Long new_level_id);

    void addNewLevelTextQuestionTextAnswer(Long levelId, String question, String answer, Date nextReplay);

    void addNewLevelTextQuestionImgAnswer(Long levelId, String question, byte[] answerImage, Date nextReplay);

    void addNewLevelImgQuestionTextAnswer(Long levelId, byte[] questionImage, String answer, Date nextReplay);

    void addNewLevelImgQuestionImgAnswer(Long levelId, byte[] questionImage, byte[] answerImage, Date nextReplay);

    void updateCardByQuestion(Long id, String question);

    void updateCardByQuestionImage(Long id, byte[] questionImage);

    void updateCardByAnswer(Long id, String answer);

    void updateCardByAnswerImage(Long id, byte[] answerImage);

    void updateCardByQuestionAndAnswer(Long id, String question, String answer);

    void updateCardByQuestionAndAnswerImage(Long id, String question, byte[] answerImage);

    void updateCardByQuestionImageAndAnswer(Long id, byte[] questionImage, String answer);

    void updateCardByQuestionImageAndAnswerImage(Long id, byte[] questionImage, byte[] answerImage);
}
