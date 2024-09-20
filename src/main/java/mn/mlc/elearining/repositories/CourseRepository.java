package mn.mlc.elearining.repositories;

import mn.mlc.elearining.entities.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByCategory_Id(Long id);
    @Query("SELECT c FROM Course c ORDER BY size(c.comments) DESC")
    List<Course> findMostCommented(Pageable pageable);
}
