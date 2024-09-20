package mn.mlc.elearining.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mn.mlc.elearining.entities.enums.CategoryEnum;

public class AddCourseDTO {
    @NotNull
    @Size(min = 2)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private String instructorName;

    public String getTitle() {
        return title;
    }

    public AddCourseDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddCourseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddCourseDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public AddCourseDTO setInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }
}
