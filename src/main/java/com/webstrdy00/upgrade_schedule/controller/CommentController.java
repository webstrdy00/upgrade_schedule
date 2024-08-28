package com.webstrdy00.upgrade_schedule.controller;

import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentRequestDto;
import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentResponseDto;
import com.webstrdy00.upgrade_schedule.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 댓글 생성 코드
     * @param scheduleId
     * @param requestDto
     * @return ResponseEntity 상태코드 및 responseDto
     */
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId,
                                                            @RequestBody CommentRequestDto requestDto){
        CommentResponseDto responseDto = commentService.createComment(scheduleId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * 댓글 조회 코드
     * @param scheduleId
     * @param commentId
     * @return responseDto
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long scheduleId, @PathVariable Long commentId){
        CommentResponseDto responseDto = commentService.getComment(scheduleId, commentId);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 모든 댓글 조회 코드
     * @param scheduleId
     * @return List<responseDto>
     */
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComment(@PathVariable Long scheduleId){
        List<CommentResponseDto> responseDtoList = commentService.getAllComment(scheduleId);
        return ResponseEntity.ok(responseDtoList);
    }

    /**
     * 댓글 수정 코드
     * @param scheduleId
     * @param commentId
     * @param requestDto
     * @return responseDto
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long scheduleId,
                                                            @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto){
        CommentResponseDto responseDto = commentService.updateComment(scheduleId, commentId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 댓글 삭제 코드
     * @param scheduleId
     * @param commentId
     * @return noContent()
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId){
        commentService.deleteComment(scheduleId, commentId);
        return ResponseEntity.noContent().build();
    }
}
