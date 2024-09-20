package mn.mlc.elearining.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity{
    private String title;
    private String description;
    @ManyToOne
    private User instructor;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public Course setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getInstructor() {
        return instructor;
    }

    public Course setInstructor(User instructor) {
        this.instructor = instructor;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Course setCategory(Category category) {
        this.category = category;
        return this;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Course setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }
}
