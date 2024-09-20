package mn.mlc.elearining.services;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import mn.mlc.elearining.entities.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<CategoryModel> getAllCategories();
    Category findByName(CategoryEnum name);
    Category findById(Long id);
}
