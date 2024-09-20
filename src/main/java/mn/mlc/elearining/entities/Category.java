package mn.mlc.elearining.entities;

import jakarta.persistence.*;
import mn.mlc.elearining.entities.enums.CategoryEnum;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    public List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public Category setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
