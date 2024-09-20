package mn.mlc.elearining.controllers.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import mn.mlc.elearining.entities.dtos.CommentMessageDTO;
import mn.mlc.elearining.entities.view.CommentDisplayView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CommentRestControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private Long courseId;
    private String username;
    @Autowired
    CommentRestControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }
    @BeforeEach
    public void setUp(){
        courseId = 3L;
        username="testUser";
    }

    @Test
    @WithMockUser(username = "testUser",authorities = {"USER"})
    void getComments() throws Exception {
        mockMvc.perform(get("/api/{courId}/comments",courseId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testCreateComment() throws Exception {
        // Create a CommentMessageDTO
        CommentMessageDTO commentMessageDTO = new CommentMessageDTO();
        commentMessageDTO.setMessage("This is a test comment.");

        // Test POST /{courseId}/comments
        MvcResult result = mockMvc.perform(post("/api/{courseId}/comments", courseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMessageDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", String.format("/api/%d/comments/", courseId)))
                .andReturn();

        // Deserialize the response
        String responseContent = result.getResponse().getContentAsString();
        CommentDisplayView createdComment = objectMapper.readValue(responseContent, CommentDisplayView.class);

        // Check that the comment was created successfully
        assert createdComment.getMessage().equals(commentMessageDTO.getMessage());

        // Test that the comment is now present
        mockMvc.perform(get("/api/{courseId}/comments", courseId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))); // Now expect 1 comment
    }
    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testCreateCommentWithInvalidData() throws Exception {
        // Create an invalid CommentMessageDTO (too short message)
        CommentMessageDTO commentMessageDTO = new CommentMessageDTO();
        commentMessageDTO.setMessage(""); // Invalid, empty message

        // Test POST /{courseId}/comments with invalid data
        mockMvc.perform(post("/api/{courseId}/comments", courseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMessageDTO)))
                .andExpect(status().isBadRequest()); // Expecting a bad request due to validation failure
    }
    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testGetCommentsWithInvalidCourseId() throws Exception {
        // Test GET /{courseId}/comments with an invalid courseId
        mockMvc.perform(get("/api/{courseId}/comments", 9999L) // Non-existent courseId
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Expecting 404 Not Found
                .andExpect(jsonPath("$.errorCode").value(1004))
                .andExpect(jsonPath("$.message").value("Such course doesn't exists"));
    }
}