package com.webstrdy00.upgrade_schedule.controller;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleRequestDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 생성 코드
     * @param requestDto
     * @return ResponseEntity 상태코드 및 responseDto
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    /**
     * 일정 조회 코드
     * @param id
     * @return responseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id){
        ScheduleResponseDto schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    /**
     * 일정 수정 코드
     * @param id
     * @param requestDto
     * @return responseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto updateSchedule = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(updateSchedule);
    }
}
