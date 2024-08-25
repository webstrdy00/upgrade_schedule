package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserBriefDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.entity.UserSchedule;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<UserBriefDto> assignedUsers;

    public static ScheduleListResponseDto fromEntity(Schedule schedule){
        ScheduleListResponseDto dto = new ScheduleListResponseDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setCommentCount(schedule.getComments().size());
        dto.setCreatedAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        dto.setAssignedUsers(schedule.getUserScheduleList().stream()
                .map(userSchedule ->  UserBriefDto.fromEntity(userSchedule.getUser()))
                .collect(Collectors.toList()));
        return dto;
    }

}
