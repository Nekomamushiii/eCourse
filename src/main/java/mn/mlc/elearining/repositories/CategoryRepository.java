package mn.mlc.elearining.repositories;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(CategoryEnum name);

}
