package com.webstrdy00.upgrade_schedule.dto.userDto;

import com.webstrdy00.upgrade_schedule.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;

    public User toEntity(){
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        return user;
    }

    public boolean hasUsername(){
        return username != null;
    }

    public boolean hasEmail(){
        return email != null;
    }
}
