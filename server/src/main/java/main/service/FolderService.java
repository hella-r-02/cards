package main.service;

import java.util.List;

import main.entity.Folder;

public interface FolderService {
    List<Folder> findAllFolders();

    List<Folder> findFoldersByCategoryId(Long id);

    Folder findById(Long id);

    Folder findByLevelId(Long id);

    void deleteById(Long id);
}
