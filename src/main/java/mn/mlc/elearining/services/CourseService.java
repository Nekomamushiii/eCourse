package mn.mlc.elearining.services;

import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.dtos.AddCourseDTO;
import mn.mlc.elearining.entities.model.CourseModel;
import mn.mlc.elearining.entities.view.CourseDetailsView;

import java.util.List;

public interface CourseService {
    List<CourseModel> getMostCommentedCourse();
    List<CourseModel> getAllCourseForCategory(Long categoryId);
    void addCourse(AddCourseDTO addCourseDTO);
    Course getById(Long id);
    CourseDetailsView getCourseById(Long id);
}
