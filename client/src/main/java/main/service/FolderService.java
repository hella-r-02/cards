package main.service;

import main.entity.Folder;

public interface FolderService {

    Folder getFolderById(Long id);

    Folder getFolderByLevelId(Long id);
}
