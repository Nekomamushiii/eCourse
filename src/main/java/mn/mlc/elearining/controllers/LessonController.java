package mn.mlc.elearining.controllers;

import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.dtos.AddLessonDTO;
import mn.mlc.elearining.services.CourseService;
import mn.mlc.elearining.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;
    @Autowired
    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }
    @GetMapping("/all/{id}")
    public String allCoursesForCategory(@PathVariable("id") Long id,Model model){
        model.addAttribute("lessons",lessonService.getAllLessonsForCourse(id));
        model.addAttribute("course",courseService.getCourseById(id));
        return "lessons";
    }
    @GetMapping("/delete")
    public String deleteLesson(@RequestParam("id") Long id){
        lessonService.deleteLesson(id);
        return "redirect:/subject/all";
    }
}
