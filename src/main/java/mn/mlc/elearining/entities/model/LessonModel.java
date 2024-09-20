package mn.mlc.elearining.entities.model;

public class LessonModel {
    private Long id;
    private String title;
    private String youtubeUrl;
    private int duration;

    public Long getId() {
        return id;
    }

    public LessonModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LessonModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public LessonModel setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public LessonModel setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
