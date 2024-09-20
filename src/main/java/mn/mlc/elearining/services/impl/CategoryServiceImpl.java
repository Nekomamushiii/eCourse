package mn.mlc.elearining.services.impl;

import jakarta.annotation.PostConstruct;
import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import mn.mlc.elearining.entities.model.CategoryModel;
import mn.mlc.elearining.exceptions.CategoryNotFoundException;
import mn.mlc.elearining.repositories.CategoryRepository;
import mn.mlc.elearining.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @PostConstruct
    public void initCategory(){
        if(this.categoryRepository.count() ==0){
            Arrays.stream(CategoryEnum.values())
                    .map(name -> new Category().setName(name))
                    .forEach(this.categoryRepository::saveAndFlush);
        }
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return this.categoryRepository.findAll()
                .stream().map(category -> {
                    CategoryModel categoryModel = new CategoryModel();
                    categoryModel.setId(category.getId())
                            .setName(category.getName().name())
                            .setCourses(category.getCourses())
                            .setImageUrl(String.format("/img/cat-%d.jpg",category.getId()));
                    return categoryModel;
                }).toList();
    }

    @Override
    public Category findByName(CategoryEnum name) {
        return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
