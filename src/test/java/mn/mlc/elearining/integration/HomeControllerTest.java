package mn.mlc.elearining.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
    private static final String HOME_CONTROLLER_PREFIX="/";
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testShouldReturnValidStatusNameForNotLoggedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                HOME_CONTROLLER_PREFIX))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}
