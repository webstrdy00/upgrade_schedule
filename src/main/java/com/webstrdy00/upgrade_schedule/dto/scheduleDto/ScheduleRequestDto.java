package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String title;
    private String content;

    public boolean hasTitle(){
        return title != null;
    }

    public boolean hasContent(){
        return content != null;
    }

    public Schedule toEntity(){
        Schedule schedule = new Schedule();
        schedule.setTitle(this.title);
        schedule.setContent(this.content);
        return schedule;
    }
}
