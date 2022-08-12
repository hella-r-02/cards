package main.service;

import main.dto.LevelDto;
import main.entity.Level;

public interface LevelService {

    Level[] getLevelsByFolderId(Long id);

    Level getLevelWithCardsThatReadyToRepeat(Long id);

    void updateDate(Long id);

    LevelDto[] getIsNotEmptyLevels();

    void addLevelsByFolderId(Long id, String date, int currentNumOfLevels);

    Level findLevelByFolderIdAndNumOFLevel(Long folderId, int numOfLevels);

    void deleteLevel(Long id);
}
