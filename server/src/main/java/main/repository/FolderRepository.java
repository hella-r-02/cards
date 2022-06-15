package main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Folder;

@Repository
public interface FolderRepository extends CrudRepository<Folder,Long> {

    @Query(value = "select * from folders " +
            "join levels l on l.folder_id = folders.id and l.id=:id", nativeQuery = true)
    Optional<Folder> findByLevelId(Long id);

    List<Folder> findByCategoryId(Long id);
}
