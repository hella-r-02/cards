package main.service.impl;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Folder> findFoldersByCategoryId(Long id) {
        return folderRepository.findByCategoryId(id);
    }

    @Override
    public Folder findById(Long id) {
        Optional<Folder> folderOptional = folderRepository.findById(id);
        return folderOptional.orElse(null);
    }

    @Override
    public Folder findByLevelId(Long id) {
        Optional<Folder> folder = folderRepository.findByLevelId(id);
        return folder.orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        folderRepository.deleteById(id);
    }

    @Override
    public void updateFolder(Long id, String name, int numOfLevels) {
        folderRepository.updateFolder(id, name, numOfLevels);
    }
}


