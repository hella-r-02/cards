package main.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.Folder;

@Repository
public interface FolderRepository extends CrudRepository<Folder, Long> {

    @Query(value = "select * from folders " +
            "join levels l on l.folder_id = folders.id and l.id=:id", nativeQuery = true)
    Optional<Folder> findByLevelId(Long id);

    List<Folder> findByCategoryId(Long id);

    @Modifying
    @Transactional
    @Query(value = "update folders set name=:name, num_of_levels=:numOfLevels " +
            "where folders.id=:id", nativeQuery = true)
    void updateFolder(@Param("id") Long id, @Param("name") String name, @Param("numOfLevels") int numOfLevels);

    @Modifying
    @Transactional
    @Query(value = "insert into folders (category_id, name, num_of_levels) " +
            "values (:category_id,:name,:num_of_levels)", nativeQuery = true)
    void addNewLevel(@Param("category_id") Long categoryId, @Param("name") String name, @Param("num_of_levels") int numOfLevels);

    List<Folder> findByName(String name);
}
