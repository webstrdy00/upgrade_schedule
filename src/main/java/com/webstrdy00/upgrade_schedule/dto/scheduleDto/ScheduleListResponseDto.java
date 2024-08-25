package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScheduleListResponseDto {
    private Long id;
    private String title;
    private String content;
    private int commentCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public static ScheduleListResponseDto fromEntity(Schedule schedule){
        ScheduleListResponseDto dto = new ScheduleListResponseDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setCommentCount(schedule.getComments().size());
        dto.setCreatedAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        return dto;
    }

}
