package mn.mlc.elearining.controllers;

import mn.mlc.elearining.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private final CategoryService categoryService;
    @Autowired
    public SubjectController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ModelAndView subjects(ModelAndView modelAndView){
        modelAndView.setViewName("subject");
        modelAndView.addObject("categories",categoryService.getAllCategories());
        return modelAndView;
    }

}
