package mn.mlc.elearining.services.impl;

import mn.mlc.elearining.entities.Comment;
import mn.mlc.elearining.entities.Course;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.CommentCreationDTO;
import mn.mlc.elearining.entities.view.CommentDisplayView;
import mn.mlc.elearining.repositories.CommentRepository;
import mn.mlc.elearining.repositories.CourseRepository;
import mn.mlc.elearining.services.CommentService;
import mn.mlc.elearining.services.CourseService;
import mn.mlc.elearining.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CourseService courseService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CourseService courseService, UserService userService, CommentRepository commentRepository) {
        this.courseService = courseService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDisplayView> getAllCommentsForCourse(Long courseId) {
        Course course = courseService.getById(courseId);
        List<Comment> comments =commentRepository.findAllByCourse_Id(course.getId()).orElse(null);
        if(comments == null)
            return null;
        return comments.stream()
                .map(comment -> new CommentDisplayView(comment.getId(),comment.getAuthor().getUsername(),comment.getTextContent()))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDisplayView createComment(CommentCreationDTO commentCreationDTO) {
        User author = userService.findByUsername(commentCreationDTO.getUsername());
        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        Course course = courseService.getById(commentCreationDTO.getCourseId());
        comment.setCourse(course);
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setTextContent(commentCreationDTO.getMessage());
        commentRepository.save(comment);
        return new CommentDisplayView(comment.getId(), author.getUsername(), comment.getTextContent());
    }
}
