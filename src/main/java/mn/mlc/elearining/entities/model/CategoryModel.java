package mn.mlc.elearining.entities.model;

import mn.mlc.elearining.entities.Course;

import java.util.List;

public class CategoryModel {
    private Long id;
    private String name;
    private List<Course> courses;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public CategoryModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public CategoryModel setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CategoryModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
