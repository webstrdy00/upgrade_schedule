package com.webstrdy00.upgrade_schedule.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.entity.Comment;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public static CommentResponseDto fromEntity(Comment comment){
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUsername(comment.getUsername());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setModifiedAt(comment.getModifiedAt());
        return dto;
    }
}
