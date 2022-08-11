package main.service;

import org.springframework.web.multipart.MultipartFile;

import main.entity.Card;

public interface CardService {
    Card[] getCardsByFolderId(Long id);

    Card[] getCardsByLevelIdAndDate(Long id);

    void upLevel(Long id);

    void downLevel(Long id);

    void deleteById(Long id);

    void addCard(Long folderId,
                 MultipartFile multipartFileQuestion,
                 String textQuestion,
                 MultipartFile multipartFileAnswer,
                 String textAnswer);

    void editCard(Long cardId,
                  MultipartFile multipartFileQuestion,
                  String textQuestion,
                  MultipartFile multipartFileAnswer,
                  String textAnswer);
}
