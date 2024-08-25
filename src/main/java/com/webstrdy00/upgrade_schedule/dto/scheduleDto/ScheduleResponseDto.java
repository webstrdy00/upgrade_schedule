package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserBriefDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;
    private List<UserBriefDto> assignedUsers;


    public static ScheduleResponseDto fromEntity(Schedule schedule){
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setCreateAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        dto.setComments(schedule.getComments().stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList()));
        dto.setAssignedUsers(schedule.getUserScheduleList().stream()
                .map(userSchedule -> UserBriefDto.fromEntity(userSchedule.getUser()))
                .collect(Collectors.toList()));
        return dto;
    }
}
