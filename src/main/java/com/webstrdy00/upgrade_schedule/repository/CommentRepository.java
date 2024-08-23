package com.webstrdy00.upgrade_schedule.repository;

import com.webstrdy00.upgrade_schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);

    Optional<Comment> findByIdAndScheduleId(Long commentId, Long scheduleId);
}
