package mn.mlc.elearining.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity{
    private String title;
    private String youtubeUrl;
    private int duration;
    @ManyToOne
    private Course course;

    public String getTitle() {
        return title;
    }

    public Lesson setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public Lesson setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Lesson setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public Lesson setCourse(Course course) {
        this.course = course;
        return this;
    }
}
