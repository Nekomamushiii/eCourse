package mn.mlc.elearining.repositories;

import mn.mlc.elearining.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByCourse_Id(Long courseId);
}
