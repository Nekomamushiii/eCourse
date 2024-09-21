package mn.mlc.elearining.services.impl;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.AddCourseDTO;
import mn.mlc.elearining.entities.model.CourseModel;
import mn.mlc.elearining.entities.view.CourseDetailsView;
import mn.mlc.elearining.exceptions.CourseNotFoundException;
import mn.mlc.elearining.repositories.CourseRepository;
import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.CourseService;
import mn.mlc.elearining.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserService userService, CategoryService categoryService, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCourse(AddCourseDTO addCourseDTO) {
        Category category = categoryService.findByName(addCourseDTO.getCategory());
        User user = userService.findByUsername(addCourseDTO.getInstructorName());
        Course course = modelMapper.map(addCourseDTO,Course.class);
        course.setCategory(category)
                .setInstructor(user);
        courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public CourseDetailsView getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        CourseDetailsView courseDetailsView= modelMapper.map(course,CourseDetailsView.class);
        courseDetailsView.setInstructor(course.getInstructor().getUsername())
                .setImageUrl(String.format("/img/course-%d.jpg",course.getId()));
        return courseDetailsView;
    }

    @Override
    public List<CourseModel> getMostCommentedCourse() {
        Pageable limit = PageRequest.of(0,6);

        return courseRepository.findMostCommented(limit)
                .stream()
                .map(course -> {
                    CourseModel courseModel = new CourseModel();
                    Random random = new Random();
                    Long randomNum = random.nextLong(1,7);
                    courseModel.setId(course.getId())
                            .setTitle(course.getTitle())
                            .setImageUrl(String.format("/img/course-%d.jpg",randomNum))
                            .setInstructorName(course.getInstructor().getUsername())
                            .setLessons(course.getLessons())
                            .setComments(course.getComments());
                    return courseModel;
                }).toList();
    }

    @Override
    public List<CourseModel> getAllCourseForCategory(Long categoryId) {

        return courseRepository.findAllByCategory_Id(categoryId)
                .stream()
                .map(course -> {
                    CourseModel courseModel = new CourseModel();
                    Random random = new Random();
                    Long randomNum = random.nextLong(1,7);
                    courseModel.setId(course.getId())
                            .setTitle(course.getTitle())
                            .setImageUrl(String.format("/img/course-%d.jpg",randomNum))
                            .setInstructorName(course.getInstructor().getUsername())
                            .setLessons(course.getLessons());
                    return courseModel;
                }).toList();
    }
}
