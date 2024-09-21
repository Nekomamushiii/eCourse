package mn.mlc.elearining.unit;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.AddCourseDTO;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import mn.mlc.elearining.entities.model.CourseModel;
import mn.mlc.elearining.entities.view.CourseDetailsView;
import mn.mlc.elearining.exceptions.CourseNotFoundException;
import mn.mlc.elearining.repositories.CourseRepository;
import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.UserService;
import mn.mlc.elearining.services.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;
    private Category category;
    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Sample data setup
        category = new Category();
        category.setId(1L);
        category.setName(CategoryEnum.WEB_DESIGN);

        user = new User();
        user.setUsername("testUser");

        course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setCategory(category);
        course.setInstructor(user);
    }

    @Test
    public void testAddCourse() {
        AddCourseDTO addCourseDTO = new AddCourseDTO();
        addCourseDTO.setCategory(CategoryEnum.WEB_DESIGN);
        addCourseDTO.setInstructorName("testUser");

        when(categoryService.findByName(CategoryEnum.WEB_DESIGN)).thenReturn(category);
        when(userService.findByUsername("testUser")).thenReturn(user);
        when(modelMapper.map(addCourseDTO, Course.class)).thenReturn(course);

        courseService.addCourse(addCourseDTO);

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testGetById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getById(1L);

        assertNotNull(foundCourse);
        assertEquals(1L, foundCourse.getId());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetById_NotFound() {
        when(courseRepository.findById(12L)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.getById(12L));
        verify(courseRepository, times(1)).findById(12L);
    }

    @Test
    public void testGetCourseById() {
        CourseDetailsView courseDetailsView = new CourseDetailsView();
        courseDetailsView.setInstructor("testUser");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(modelMapper.map(course, CourseDetailsView.class)).thenReturn(courseDetailsView);

        CourseDetailsView view = courseService.getCourseById(1L);

        assertNotNull(view);
        assertEquals("testUser", view.getInstructor());
        assertEquals("/img/course-1.jpg", view.getImageUrl());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetMostCommentedCourse() {
        List<Course> courses = Arrays.asList(course);
        Pageable limit = PageRequest.of(0, 6);

        when(courseRepository.findMostCommented(limit)).thenReturn(courses);

        List<CourseModel> courseModels = courseService.getMostCommentedCourse();

        assertNotNull(courseModels);
        assertEquals(1, courseModels.size());
        verify(courseRepository, times(1)).findMostCommented(limit);
    }

    @Test
    public void testGetAllCourseForCategory() {
        List<Course> courses = Arrays.asList(course);

        when(courseRepository.findAllByCategory_Id(1L)).thenReturn(courses);

        List<CourseModel> courseModels = courseService.getAllCourseForCategory(1L);

        assertNotNull(courseModels);
        assertEquals(1, courseModels.size());
        verify(courseRepository, times(1)).findAllByCategory_Id(1L);
    }
}

