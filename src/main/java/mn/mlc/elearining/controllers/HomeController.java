package mn.mlc.elearining.controllers;

import mn.mlc.elearining.repositories.CourseRepository;
import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CategoryService categoryService;
    private  final CourseService courseService;
    @Autowired
    public HomeController(CategoryService categoryService, CourseService courseService) {
        this.categoryService = categoryService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("subjects",categoryService.getAllCategories());
        model.addAttribute("mostCommented",courseService.getMostCommentedCourse());
        return "index";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
