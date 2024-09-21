package mn.mlc.elearining.unit;

import mn.mlc.elearining.entities.Category;
import mn.mlc.elearining.entities.enums.CategoryEnum;
import mn.mlc.elearining.entities.model.CategoryModel;
import mn.mlc.elearining.exceptions.CategoryNotFoundException;
import mn.mlc.elearining.repositories.CategoryRepository;
import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private List<Category> categories;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Sample category setup
        category = new Category();
        category.setId(1L);
        category.setName(CategoryEnum.WEB_DESIGN);
        category.setCourses(null);

        categories = Arrays.asList(category);
    }

    @Test
    public void testGetAllCategories() {
        // Mock repository to return the sample categories
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryModel> categoryModels = categoryService.getAllCategories();

        assertNotNull(categoryModels);
        assertEquals(1, categoryModels.size());
        assertEquals("WEB_DESIGN", categoryModels.get(0).getName());
        assertEquals(1L, categoryModels.get(0).getId());
        assertEquals("/img/cat-1.jpg", categoryModels.get(0).getImageUrl());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testFindByName() {
        when(categoryRepository.findByName(CategoryEnum.WEB_DESIGN)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findByName(CategoryEnum.WEB_DESIGN);

        assertNotNull(foundCategory);
        assertEquals(CategoryEnum.WEB_DESIGN, foundCategory.getName());
        verify(categoryRepository, times(1)).findByName(CategoryEnum.WEB_DESIGN);
    }


    @Test
    public void testFindById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findById(1L);

        assertNotNull(foundCategory);
        assertEquals(1L, foundCategory.getId());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.findById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testInitCategory() {
        // Simulate no categories in the database
        when(categoryRepository.count()).thenReturn(0L);

        // Call the @PostConstruct method directly
        categoryService.initCategory();

        verify(categoryRepository, times(CategoryEnum.values().length)).saveAndFlush(any(Category.class));
    }

    @Test
    public void testInitCategory_AlreadyPopulated() {
        // Simulate existing categories in the database
        when(categoryRepository.count()).thenReturn(10L);

        categoryService.initCategory();

        // Verify no categories are added if count > 0
        verify(categoryRepository, times(0)).saveAndFlush(any(Category.class));
    }
}

