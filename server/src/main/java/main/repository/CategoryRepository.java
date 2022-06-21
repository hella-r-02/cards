package main.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "select * from categories " +
            "join folders f on f.category_id = categories.id and f.id=:id", nativeQuery = true)
    Optional<Category> findByFolderId(Long id);

    @Modifying
    @Transactional
    @Query(value = "update categories set name=:name " +
            "where categories.id=:id", nativeQuery = true)
    void updateCategory(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into categories (name) " +
            "values (:name)", nativeQuery = true)
    void addCategory(@Param("name") String name);
}
