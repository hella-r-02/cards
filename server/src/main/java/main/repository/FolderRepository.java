package main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Folder;

@Repository
public interface FolderRepository extends CrudRepository<Folder,Long> {
    List<Folder> findByName(String name);

    List<Folder> findByCategoryId(Long id);
}
