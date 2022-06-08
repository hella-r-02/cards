package main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Level;
import main.repository.LevelRepository;
import main.service.LevelService;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;

    @Override
    public List<Level> findLevelsByFolderId(Long id) {
        return levelRepository.findByFolderId(id);
    }
}
