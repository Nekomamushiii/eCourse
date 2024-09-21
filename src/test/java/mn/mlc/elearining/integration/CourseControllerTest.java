package mn.mlc.elearining.integration;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import mn.mlc.elearining.repositories.CategoryRepository;
import mn.mlc.elearining.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    private static final String COURSE_CONTROLLER_PREFIX="/course";
    private static final Long CATEGORY_ID=1L;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @BeforeEach
    public void setUp(){
        Category category=categoryRepository.findById(CATEGORY_ID).get();
        Course course = new Course();
        course.setTitle("TestCourse")
                .setCategory(category);

    }
    @Test
    @WithMockUser(value = "courseTest",roles = {"USER","ADMIN"})
    void testShouldReturnOkStatusForShowAllCourses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                COURSE_CONTROLLER_PREFIX+"/all/{id}",CATEGORY_ID ))
                .andExpect(status().isOk())
                .andExpect(view().name("course"))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attributeExists("courses"));
    }
}
