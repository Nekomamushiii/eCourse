package mn.mlc.elearining.entities.view;

public class CommentDisplayView {
    private Long id;
    private String authorName;
    private String message;

    public CommentDisplayView(Long id, String authorName, String message) {
        this.id = id;
        this.authorName = authorName;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public CommentDisplayView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentDisplayView setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentDisplayView setMessage(String message) {
        this.message = message;
        return this;
    }
}
