package mn.mlc.elearining.services;

import mn.mlc.elearining.entities.dtos.CommentCreationDTO;
import mn.mlc.elearining.entities.view.CommentDisplayView;

import java.util.List;

public interface CommentService {
    List<CommentDisplayView> getAllCommentsForCourse(Long courseId);
    CommentDisplayView createComment(CommentCreationDTO commentCreationDTO);
}
