package main.service;

import java.util.Date;
import java.util.List;

import main.entity.Level;

public interface LevelService {
    List<Level> findLevelsByFolderId(Long id);

    Level findNextLevel(Long id, Long num_of_level);

    Level findPrevLevel(Long id, Long num_of_level);

    Level getLevelWithCardsThatReadyToRepeat(Long id);

    void updateLevel(Long id);

    List<Level> getAllLevels();

    void deleteById(Long id);

    void addNewLevel(Long folderId, Date date, int numOfLevels);

    Level findLevelByFolderIdAndNumOFLevel(Long folderId, int numOfLevels);

    Level findById(Long id);
}
