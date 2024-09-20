package mn.mlc.elearining.entities.dtos;

public class CommentCreationDTO {
    private String username;
    private Long courseId;
    private String message;

    public CommentCreationDTO() {
    }

    public CommentCreationDTO(String username, Long courseId, String message) {
        this.username = username;
        this.courseId = courseId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public CommentCreationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public CommentCreationDTO setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentCreationDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
