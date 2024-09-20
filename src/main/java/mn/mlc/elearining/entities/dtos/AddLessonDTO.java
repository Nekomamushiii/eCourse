package mn.mlc.elearining.entities.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddLessonDTO {
    @NotNull
    @Size(min = 2)
    private String title;
    private String youtubeUrl;
    private int duration;

    public String getTitle() {
        return title;
    }

    public AddLessonDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public AddLessonDTO setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AddLessonDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
