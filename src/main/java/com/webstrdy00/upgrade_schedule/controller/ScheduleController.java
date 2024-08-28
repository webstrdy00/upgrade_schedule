package com.webstrdy00.upgrade_schedule.controller;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleListResponseDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleRequestDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserBriefDto;
import com.webstrdy00.upgrade_schedule.entity.UserRoleEnum;
import com.webstrdy00.upgrade_schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 생성 코드
     * @param requestDto
     * @param userId 일정 작성자의 ID
     * @return ResponseEntity 상태코드 및 responseDto
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto,
                                                              @RequestParam Long userId){
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(requestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    /**
     * 특정 일정 조회 코드
     * @param scheduleId
     * @return responseDto
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleId){
        ScheduleResponseDto schedule = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.ok(schedule);
    }

    /**
     * 일정 전체 조회(페이징 적용)
     * @param page
     * @param size
     * @return schedulePage
     */
    @GetMapping
    public ResponseEntity<Page<ScheduleListResponseDto>> getAllSchedules(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ScheduleListResponseDto> shedulePage = scheduleService.getAllSchedules(pageable);
        return ResponseEntity.ok(shedulePage);
    }

    /**
     * 특정 일정에 할당된 모든 사용자를 조회하는 코드
     * @param scheduleId
     * @return List<UserResponseDto>
     */
    @GetMapping("/{scheduleId}/users")
    public ResponseEntity<List<UserBriefDto>> getUserAssignedToSchedule(@PathVariable Long scheduleId){
        List<UserBriefDto> assignedUsers = scheduleService.getUserAssignedToSchedule(scheduleId);
        return ResponseEntity.ok(assignedUsers);
    }

    /**
     * 일정 수정 코드
     * @param scheduleId
     * @param requestDto
     * @return responseDto
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto, HttpServletRequest request){
        UserRoleEnum userRole = (UserRoleEnum) request.getAttribute("userRole");
        if (userRole != UserRoleEnum.ADMIN)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        ScheduleResponseDto updateSchedule = scheduleService.updateSchedule(scheduleId, requestDto);
        return ResponseEntity.ok(updateSchedule);
    }

    /**
     * 특정 일정에 사용자를 할당하는 코드
     * @param scheduleId
     * @param userId
     * @return ScheduleResponseDto
     */
    @PutMapping("/{scheduleId}/assign")
    public ResponseEntity<ScheduleResponseDto> assignUserToSchedule(@PathVariable Long scheduleId,
                                                     @RequestParam Long userId){
        ScheduleResponseDto updateSchedule = scheduleService.assignUserToSchedule(scheduleId, userId);
        return ResponseEntity.ok(updateSchedule);
    }

    /**
     * 일정 삭제 코드 (영속성 전이 적용)
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpServletRequest request){
        UserRoleEnum userRole = (UserRoleEnum) request.getAttribute("userRole");
        if (userRole != UserRoleEnum.ADMIN)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 특정 일정에서 사용자를 제거하는 코드
     * @param scheduleId
     * @param userId
     * @return ScheduleResponseDto
     */
    @DeleteMapping("/{scheduleId}/users/{userId}")
    public ResponseEntity<ScheduleResponseDto> removeUserFromSchedule(@PathVariable Long scheduleId,
                                                                      @PathVariable Long userId){
        ScheduleResponseDto updatedSchedule = scheduleService.removeUserFromSchedule(scheduleId, userId);
        return ResponseEntity.ok(updatedSchedule);
    }
}
