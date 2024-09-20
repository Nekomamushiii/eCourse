package mn.mlc.elearining.entities.view;

import mn.mlc.elearining.entities.Lesson;

import java.util.List;

public class CourseDetailsView {
    private Long id;
    private String title;
    private String description;
    private String instructor;
    private List<Lesson> lessons;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public CourseDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDetailsView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getInstructor() {
        return instructor;
    }

    public CourseDetailsView setInstructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public CourseDetailsView setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseDetailsView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
