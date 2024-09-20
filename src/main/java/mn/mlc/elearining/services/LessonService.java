package mn.mlc.elearining.services;

import mn.mlc.elearining.entities.Lesson;
import mn.mlc.elearining.entities.dtos.AddLessonDTO;
import mn.mlc.elearining.entities.model.LessonModel;

import java.util.List;

public interface LessonService {
    void addLessonToCourse(AddLessonDTO addLessonDTO,Long courseId);
    List<LessonModel> getAllLessonsForCourse(Long courseId);
    Lesson getById(Long id);
    void deleteLesson(Long id);
}
