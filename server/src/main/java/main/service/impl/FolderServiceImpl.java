package main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Folder;
import main.repository.FolderRepository;
import main.service.FolderService;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository folderRepository;

    @Override
    public List<Folder> findAllFolders() {
        return (List<Folder>) folderRepository.findAll();
    }
}
