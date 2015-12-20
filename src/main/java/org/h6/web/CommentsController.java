package org.h6.web;

import org.h6.domain.task.Comment;
import org.h6.dto.CommentBody;
import org.h6.repository.CommentsRepository;
import org.h6.service.TaskService;
import org.h6.service.UserService;
import org.h6.service.UsersNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private UserService usersSerivce;

    @Autowired
    private TaskService taskService;


    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersNotifier notifier;


    @RequestMapping(value = "api/tasks/{id}/add_comment", method = RequestMethod.POST)
    public Comment createComment(
            @PathVariable("id") long taskId,
            @RequestBody CommentBody commentBody) {

        Comment comment = new Comment();
        comment.setCreationTime(Timestamp.from(ZonedDateTime.now().toInstant()));
        comment.setText(commentBody.getText());
        comment.setTaskId(taskId);

        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        comment.setUser(usersSerivce.findByUsername(username));

        commentsRepository.saveAndFlush(comment);

        // temporary api
        notifier.notifyAll(taskService.findOne(taskId), new UsersNotifier.Context() {
            {
                put("type", Comment.class);
                put("username", username);
                put("comment-text", commentBody.getText());
            }
        });
        return comment;
    }

    @RequestMapping(value = "api/tasks/{id}/comments", method = RequestMethod.GET)
    public List<Comment> findComments(
            @PathVariable("id") long taskId) {
        return commentsRepository.findAllByTaskId(taskId);
    }
}
