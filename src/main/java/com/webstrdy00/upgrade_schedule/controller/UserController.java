package com.webstrdy00.upgrade_schedule.controller;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserRequestDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserResponseDto;
import com.webstrdy00.upgrade_schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 유저 생성 코드
     * @param requestDto
     * @return ResponseEntity 상태코드 및 responseDto
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto createUser = userService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    /**
     * 사용자에게 일정을 할당하는 코드
     * @param userId
     * @param scheduleId
     * @return UserResponseDto
     */
    @PostMapping("/{userId}/schedules")
    public ResponseEntity<UserResponseDto> assignScheduleToUser(@PathVariable Long userId,
                                                                @RequestParam Long scheduleId){
        UserResponseDto updateUser = userService.assignScheduleToUser(userId, scheduleId);
        return ResponseEntity.ok(updateUser);
    }

    /**
     * 특정 유저 조회 코드
     * @param userId
     * @return UserResponseDto
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId){
        UserResponseDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * 전체 유저 조회 코드
     * @return List<UserResponseDto>
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    /**
     * 사용자의 모든 일정을 조회하는 코드
     * @param userId
     * @return List<ScheduleBriefDto>
     */
    @GetMapping("/{userId}/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getUserSchedules(@PathVariable Long userId){
        List<ScheduleResponseDto> userSchedule = userService.getUserSchedules(userId);
        return ResponseEntity.ok(userSchedule);
    }

    /**
     * 유저 수정 코드
     * @param userId
     * @param requestDto
     * @return UserResponseDto
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto requestDto){
        UserResponseDto updateUser = userService.updateUser(userId, requestDto);
        return ResponseEntity.ok(updateUser);
    }

    /**
     * 유저 삭제 코드
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 사용자로부터 일정을 제거하는 코드
     * @param userId
     * @param scheduleId
     * @return UserResponseDto
     */
    @DeleteMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<UserResponseDto> removeScheduleFromUser(@PathVariable Long userId,
                                                                  @PathVariable Long scheduleId){
        UserResponseDto updatedUser = userService.removeScheduleFromUser(userId, scheduleId);
        return ResponseEntity.ok(updatedUser);
    }
}
