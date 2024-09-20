package mn.mlc.elearining.controllers;

import mn.mlc.elearining.entities.dtos.UserRegisterDTO;
import mn.mlc.elearining.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userRegisterDTO"));
    }

    @Test
    public void testUserRegister_Success() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("testUser");
        userRegisterDTO.setPassword("testPassword");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", userRegisterDTO.getUsername())
                        .param("password", userRegisterDTO.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

        verify(userService).userRegister(any(UserRegisterDTO.class));
    }

    @Test
    public void testUserRegister_Failure() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "")  // Invalid input to trigger validation error
                        .param("password", "testPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));

        // In a real scenario, you'd check for validation errors in the model
    }

    @Test
    public void testTeacherPage() throws Exception {
        mockMvc.perform(get("/teacher"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher"));
    }
}
