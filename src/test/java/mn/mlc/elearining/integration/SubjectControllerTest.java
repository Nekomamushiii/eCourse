package mn.mlc.elearining.integration;

import mn.mlc.elearining.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {
    private static final String SUBJECT_CONTROLLER_PREFIX="/subject";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShouldReturnOkStatusForShowAllCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SUBJECT_CONTROLLER_PREFIX+"/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("subject"));
    }
}
