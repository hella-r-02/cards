package main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "select * from categories " +
            "join folders f on f.category_id = categories.id and f.id=:id", nativeQuery = true)
    Optional<Category> findByFolderId(Long id);
}
