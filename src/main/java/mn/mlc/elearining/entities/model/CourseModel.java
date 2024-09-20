package mn.mlc.elearining.entities.model;

import mn.mlc.elearining.entities.Comment;
import mn.mlc.elearining.entities.Lesson;

import java.util.List;

public class CourseModel {
    private Long id;
    private String title;
    private String instructorName;
    private String imageUrl;
    private List<Lesson> lessons;
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public CourseModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public CourseModel setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public CourseModel setInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public CourseModel setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }
}
