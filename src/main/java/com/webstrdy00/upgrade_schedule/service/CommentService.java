package com.webstrdy00.upgrade_schedule.service;

import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentRequestDto;
import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Comment;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.repository.CommentRepository;
import com.webstrdy00.upgrade_schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        Comment comment = requestDto.toEntity(schedule);

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.fromEntity(savedComment);
    }

    public CommentResponseDto getComment(Long scheduleId, Long commentId) {
    Comment comment = findCommentByIdAndScheduleId(commentId, scheduleId);

        return CommentResponseDto.fromEntity(comment);
    }

    public List<CommentResponseDto> getAllComment(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = findCommentByIdAndScheduleId(commentId, scheduleId);

        if (requestDto.hasContent())
            comment.setContent(requestDto.getContent());
        if (requestDto.hasUsername())
            comment.setUsername(requestDto.getUsername());

        Comment updateComment = commentRepository.save(comment);
        return CommentResponseDto.fromEntity(updateComment);
    }

    public void deleteComment(Long scheduleId, Long commentId) {
        Comment comment = findCommentByIdAndScheduleId(commentId, scheduleId);

        commentRepository.delete(comment);
    }
    
    // 댓글이 일정에 포함되어있는지 확인하는 메서드
    private Comment findCommentByIdAndScheduleId(Long commentId, Long scheduleId){
        return commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("해당 일정에 속한 댓글을 찾을 수 없습니다."));
    }
}
