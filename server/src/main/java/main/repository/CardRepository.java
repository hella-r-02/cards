package main.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query(value = "update cards set level_id=:level_id " +
            "where cards.id=:id", nativeQuery = true)
    void updateCard(@Param("id") Long id, @Param("level_id") Long level_id);

    @Modifying
    @Transactional
    @Query(value = "update cards set level_id=:level_id, next_replay=:date " +
            "where cards.id=:id", nativeQuery = true)
    void updateCardByDateAndLevel(@Param("id") Long id, @Param("level_id") Long level_id, @Param("date") Date date);

    @Modifying
    @Transactional
    @Query(value = "update cards set level_id=:new_level_id " +
            "where cards.level_id=:level_id", nativeQuery = true)
    void updateCardsByLevel(@Param("level_id") Long level_id, @Param("new_level_id") Long new_level_id);
}
