package main.service;

import java.util.List;

import main.entity.Level;

public interface LevelService {

    List<Level> findLevelsByFolderId(Long id);
}
