package main.service;

import java.util.List;

import main.entity.Folder;

public interface FolderService {
    List<Folder> findAllFolders();
}
