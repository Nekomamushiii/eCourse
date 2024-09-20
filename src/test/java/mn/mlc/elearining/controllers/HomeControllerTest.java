package mn.mlc.elearining.controllers;

import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        // Mocking the categoryService and courseService responses
        Mockito.when(categoryService.getAllCategories()).thenReturn(Collections.emptyList()); // Mocking empty category list
        Mockito.when(courseService.getMostCommentedCourse()).thenReturn(Collections.emptyList()); // Mocking empty most commented list
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Expect HTTP 200 status
                .andExpect(view().name("index")) // Expect "index" view
                .andExpect(model().attributeExists("subjects")) // Expect "subjects" attribute in the model
                .andExpect(model().attributeExists("mostCommented")); // Expect "mostCommented" attribute in the model
    }
}
