package com.webstrdy00.upgrade_schedule.service;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleListResponseDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleRequestDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = requestDto.toEntity();
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return ScheduleResponseDto.fromEntity(savedSchedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        return ScheduleResponseDto.fromEntity(schedule);
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("일정을 찾을 수 없습니다."));
        if (requestDto.hasUsername())
            schedule.setUsername(requestDto.getUsername());
        if(requestDto.hasTitle())
            schedule.setTitle(requestDto.getTitle());
        if (requestDto.hasContent())
            schedule.setContent(schedule.getContent());

        Schedule updateSchedule = scheduleRepository.save(schedule);
        return ScheduleResponseDto.fromEntity(updateSchedule);
    }

    public Page getAllSchedules(Pageable pageable) {
        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        return schedulePage.map(ScheduleListResponseDto::fromEntity);
    }
}
