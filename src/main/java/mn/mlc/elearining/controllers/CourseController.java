package mn.mlc.elearining.controllers;

import jakarta.validation.Valid;
import mn.mlc.elearining.entities.dtos.AddCourseDTO;
import mn.mlc.elearining.entities.dtos.AddLessonDTO;
import mn.mlc.elearining.entities.dtos.UserRegisterDTO;
import mn.mlc.elearining.services.CategoryService;
import mn.mlc.elearining.services.CourseService;
import mn.mlc.elearining.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final LessonService lessonService;
    private final CategoryService categoryService;

    private final CourseService courseService;
    @Autowired
    public CourseController(LessonService lessonService, CategoryService categoryService, CourseService courseService) {
        this.lessonService = lessonService;
        this.categoryService = categoryService;
        this.courseService = courseService;
    }

    @GetMapping("/all/{id}")
    public String allCoursesForCategory(@PathVariable("id") Long id,Model model){
        model.addAttribute("courses",courseService.getAllCourseForCategory(id));
        model.addAttribute("subjects",categoryService.findById(id));
        return "course";
    }
    @GetMapping("/all/{id}/lesson/add")
    public ModelAndView lessonAdd(@PathVariable("id") Long courseId, ModelAndView modelAndView){
        modelAndView.setViewName("add-lesson");
        modelAndView.addObject("addLessonDTO",new AddLessonDTO());
        modelAndView.addObject("courseId",courseId);
        return modelAndView;
    }
    @PostMapping("/all/{id}/lesson/add")
    public String addLessonToCourse(@PathVariable("id") Long courseId,AddLessonDTO addLessonDTO){
        lessonService.addLessonToCourse(addLessonDTO,courseId);
        return "redirect:/subject/all";
    }

    @GetMapping("/add")
    public String addCoursePage(Model model){
        if(!model.containsAttribute("addCourseDTO"))
            model.addAttribute("addCourseDTO",new AddCourseDTO());
        return "add-course";
    }
    @PostMapping("/add")
    public String addCourse(@Valid AddCourseDTO addCourseDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addCourseDTO",addCourseDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCourseDTO",bindingResult);
            return "redirect:add-course";
        }
        courseService.addCourse(addCourseDTO);
        return "redirect:subject/all";
    }
    @GetMapping("/details/{id}")
    public String courseDetails(@PathVariable("id") Long id,Model model){
        model.addAttribute("courseDetails",courseService.getCourseById(id));
        return "single";
    }


}
