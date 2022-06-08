package main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Level;

@Repository
public interface LevelRepository extends CrudRepository<Level,Long> {
    List<Level> findByFolderId(Long id);
}
