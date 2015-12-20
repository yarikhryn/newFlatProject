package org.h6.repository;

import org.h6.domain.task.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Integer>
{

    List<Comment> findAllByTaskId(long taskId);
}
