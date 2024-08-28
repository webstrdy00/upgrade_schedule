package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.dto.commentDto.CommentResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserBriefDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    @JsonFormat(pattern = "MM-dd")
    private LocalDate date;
    private String weather;
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
        dto.setWeather(schedule.getWeather());
        dto.setDate(schedule.getDate());
        dto.setCreateAt(schedule.getCreatedAt());
        dto.setModifiedAt(schedule.getModifiedAt());
        dto.setComments(schedule.getComments().stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }

    public static ScheduleResponseDto fromEntityWithUsers(Schedule schedule, List<UserBriefDto> assignedUsers){
        ScheduleResponseDto dto = fromEntity(schedule);
        dto.setAssignedUsers(assignedUsers);
        return dto;
    }
}
