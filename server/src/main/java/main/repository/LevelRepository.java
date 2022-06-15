package main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Level;

@Repository
public interface LevelRepository extends CrudRepository<Level, Long> {
    List<Level> findByFolderId(Long id);

    @Query(value = "select * from levels " +
            "join folders f on f.id = levels.folder_id " +
            "where folder_id in (select folder_id from folders where folder_id=:id) " +
            "and ((levels.num_of_level-1)=:num_of_level)", nativeQuery = true)
    Optional<Level> findNextLevelByFolderId(Long id, Long num_of_level);


    @Query(value = "select * from levels " +
            "join folders f on f.id = levels.folder_id " +
            "where folder_id in (select folder_id from folders where folder_id=:id) " +
            "and ((levels.num_of_level+1)=:num_of_level)", nativeQuery = true)
    Optional<Level> findPrevLevelByFolderId(Long id, Long num_of_level);

}
