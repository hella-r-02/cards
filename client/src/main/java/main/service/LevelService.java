package main.service;

import main.dto.LevelDto;
import main.entity.Level;

public interface LevelService {

    Level[] getLevelsByFolderId(Long id);

    Level getLevelWithCardsThatReadyToRepeat(Long id);

    void updateDate(Long id);

    LevelDto[] getIsNotEmptyLevels();
}
