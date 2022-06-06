package main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import main.entity.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByLevel(Long level);

    List<Card> findByFolderId(Long id);

    List<Card> findByFolderIdAndLevel(Long Id, Long level);
}
