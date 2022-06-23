package main.service;

import java.util.List;

import main.entity.Folder;

public interface FolderService {
    List<Folder> findAllFolders();

    List<Folder> findFoldersByCategoryId(Long id);

    Folder findById(Long id);

    Folder findByLevelId(Long id);

    void deleteById(Long id);

    void updateFolder(Long id, String name, int numOfLevels);

    void addFolder(Long categoryId, String name, int numOfLevels);

    List<Folder> findByName(String name);

    Folder FindByCardId(Long id);
}
