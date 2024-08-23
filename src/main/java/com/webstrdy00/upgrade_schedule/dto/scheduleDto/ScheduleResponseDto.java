package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public static ScheduleResponseDto fromEntity(Schedule schedule){
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setUsername(schedule.getUsername());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setCreateAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        return dto;
    }
}
