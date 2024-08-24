package com.webstrdy00.upgrade_schedule.controller;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleListResponseDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleRequestDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * @return ResponseEntity 상태코드 및 responseDto
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    /**
     * 특정 일정 조회 코드
     * @param id
     * @return responseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id){
        ScheduleResponseDto schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<Page<ScheduleListResponseDto>> getAllSchedules(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ScheduleListResponseDto> shedulePage = scheduleService.getAllSchedules(pageable);
        return ResponseEntity.ok(shedulePage);
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
