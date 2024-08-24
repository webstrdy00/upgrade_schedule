package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;


    public static ScheduleResponseDto fromEntity(Schedule schedule){
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setUsername(schedule.getUsername());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setCreateAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        dto.setComments(schedule.getComments().stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}
