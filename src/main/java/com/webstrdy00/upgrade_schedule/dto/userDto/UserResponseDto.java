package com.webstrdy00.upgrade_schedule.dto.userDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleBriefDto;
import com.webstrdy00.upgrade_schedule.entity.User;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    private List<ScheduleBriefDto> assignedSchedules;

    public static UserResponseDto fromEntity(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setModifiedAt(user.getModifiedAt());
        dto.setAssignedSchedules(user.getUserScheduleList().stream()
                .map(userSchedule -> ScheduleBriefDto.fromEntity(userSchedule.getSchedule()))
                .collect(Collectors.toList()));
        return dto;
    }
}
