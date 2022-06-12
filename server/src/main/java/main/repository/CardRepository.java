package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByLevelId(Long id);
    @Query(value = "select * from cards " +
            "join levels l on l.id = cards.level_id " +
            "join folders f on f.id = l.folder_id " +
            "where folder_id in (select folder_id from folders where folder_id=:id)", nativeQuery = true)
    List<Card> findByFolderId(@Param("id") Long id);
}
