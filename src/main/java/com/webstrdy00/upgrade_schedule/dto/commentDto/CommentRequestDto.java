package com.webstrdy00.upgrade_schedule.dto.commentDto;

import com.webstrdy00.upgrade_schedule.entity.Comment;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String username;

    public Comment toEntity(Schedule schedule){
        Comment comment = new Comment();
        comment.setContent(this.content);
        comment.setUsername(this.username);
        comment.setSchedule(schedule);
        return comment;
    }
    public boolean hasContent(){
        return content != null;
    }

    public boolean hasUsername(){
        return username != null;
    }


}
