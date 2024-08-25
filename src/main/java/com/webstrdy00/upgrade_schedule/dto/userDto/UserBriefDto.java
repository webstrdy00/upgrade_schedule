package com.webstrdy00.upgrade_schedule.dto.userDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.webstrdy00.upgrade_schedule.entity.User;
import lombok.Setter;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserBriefDto {
    private Long id;
    private String username;
    private String email;

    public static UserBriefDto fromEntity(User user){
        UserBriefDto dto = new UserBriefDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
