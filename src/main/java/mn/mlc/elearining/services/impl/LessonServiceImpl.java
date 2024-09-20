package mn.mlc.elearining.services.impl;

import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.Lesson;
import mn.mlc.elearining.entities.dtos.AddLessonDTO;
import mn.mlc.elearining.entities.model.LessonModel;
import mn.mlc.elearining.repositories.LessonRepository;
import mn.mlc.elearining.services.CourseService;
import mn.mlc.elearining.services.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, CourseService courseService, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addLessonToCourse(AddLessonDTO addLessonDTO, Long courseId) {
        String videoUrl = addLessonDTO.getYoutubeUrl();
        videoUrl=videoUrl.substring(17);
        Course course = courseService.getById(courseId);
        Lesson lesson = modelMapper.map(addLessonDTO,Lesson.class);
        lesson.setCourse(course);
        lesson.setYoutubeUrl(videoUrl);
        lessonRepository.save(lesson);
    }

    @Override
    public List<LessonModel> getAllLessonsForCourse(Long courseId) {
        return lessonRepository.findAllByCourse_Id(courseId)
                .stream()
                .map(lesson -> {
                    LessonModel lessonModel=new LessonModel();
                    lessonModel.setId(lesson.getId())
                            .setTitle(lesson.getTitle())
                            .setYoutubeUrl(lesson.getYoutubeUrl())
                            .setDuration(lesson.getDuration());
                    return lessonModel;
                }).toList();
    }

    @Override
    public Lesson getById(Long id) {
        return null;
    }

    @Override
    public void deleteLesson(Long id) {
        this.lessonRepository.deleteById(id);
    }
}
