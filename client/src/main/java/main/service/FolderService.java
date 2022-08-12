package main.service;

import org.springframework.web.bind.annotation.RequestParam;

import main.entity.Folder;

public interface FolderService {

    Folder getFolderById(Long id);

    Folder getFolderByLevelId(Long id);

    Folder findByCardId(Long id);

    Folder[] findFoldersByName(String name);

    Folder[] getFoldersByCategoryId(Long id);

    Folder[] getAllFolders();

    void deleteById(Long id);

    void editFolder(Long id, String name, int numOfLevels);

    void addFolder(Long categoryId, String name, int numOfLevels);
}
