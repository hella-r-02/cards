package main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.repository.CardRepository;
import main.repository.FolderRepository;
import main.service.CardService;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private FolderRepository folderRepository;
}
