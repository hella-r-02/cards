package main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Category;

@Repository
public interface CategoryRepository  extends CrudRepository<Category,Long> {

}
