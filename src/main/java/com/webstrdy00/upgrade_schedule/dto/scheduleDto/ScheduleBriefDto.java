package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Setter;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScheduleBriefDto {
    private Long id;
    private String title;

    public static ScheduleBriefDto fromEntity(Schedule schedule){
        ScheduleBriefDto dto = new ScheduleBriefDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        return dto;
    }
}
