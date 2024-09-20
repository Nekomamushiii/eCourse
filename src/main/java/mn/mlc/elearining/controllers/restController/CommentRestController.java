package mn.mlc.elearining.controllers.restController;

import mn.mlc.elearining.entities.dtos.CommentCreationDTO;
import mn.mlc.elearining.entities.dtos.CommentMessageDTO;
import mn.mlc.elearining.entities.view.CommentDisplayView;
import mn.mlc.elearining.exceptions.CourseNotFoundException;
import mn.mlc.elearining.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    private final CommentService commentService;
    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/{courseId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(commentService.getAllCommentsForCourse(courseId));
    }
    @PostMapping(value = "/{courseId}/comments",consumes = "application/json",produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("courseId") Long courseId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDTO commentMessageDTO){
        CommentCreationDTO commentCreationDTO = new CommentCreationDTO(
                userDetails.getUsername(),
                courseId,
                commentMessageDTO.getMessage()
        );
        CommentDisplayView commentDisplayView = commentService.createComment(commentCreationDTO);
        return ResponseEntity.created(URI.create(String.format("/api/%d/comments/%d",courseId,commentDisplayView.getId())))
                .body(commentDisplayView);
    }
    @ExceptionHandler({CourseNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleRouteNotFound(){
        return ResponseEntity.status(404).body(new ErrorApiResponse("Such course doesn't exists", 1004));
    }

    static class ErrorApiResponse{
        private String message;
        private Integer errorCode;

        public ErrorApiResponse(String message, Integer errorCode) {
            this.message = message;
            this.errorCode = errorCode;
        }

        public ErrorApiResponse() {
        }

        public String getMessage() {
            return message;
        }

        public ErrorApiResponse setMessage(String message) {
            this.message = message;
            return this;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public ErrorApiResponse setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }
    }
}
