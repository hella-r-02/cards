package main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Folder;
import main.exception.FolderNotFoundException;
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
        return (List<Folder>) folderRepository.findByCategoryId(id);
    }

    @Override
    public Folder findById(Long id) {
         Optional<Folder> folderOptional= folderRepository.findById(id);
         if(folderOptional.isPresent()){
             return folderOptional.get();
         }
         else{
             throw new FolderNotFoundException("folder not found");
         }
    }
}
