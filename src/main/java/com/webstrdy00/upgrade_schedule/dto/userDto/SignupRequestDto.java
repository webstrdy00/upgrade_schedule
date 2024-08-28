package com.webstrdy00.upgrade_schedule.dto.userDto;

import com.webstrdy00.upgrade_schedule.entity.User;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String email;
    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";

    public User toEntity(){
        User user = new User();
        user.setEmail(this.email);
        user.setUsername(this.username);
        return user;
    }
}
