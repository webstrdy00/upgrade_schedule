package com.webstrdy00.upgrade_schedule.service;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleListResponseDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleRequestDto;
import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserBriefDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.entity.User;
import com.webstrdy00.upgrade_schedule.repository.ScheduleRepository;
import com.webstrdy00.upgrade_schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = requestDto.toEntity();
        schedule.addUser(user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.fromEntity(savedSchedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        return ScheduleResponseDto.fromEntity(schedule);
    }

    public Page getAllSchedules(Pageable pageable) {
        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        return schedulePage.map(ScheduleListResponseDto::fromEntity);
    }

    public List<UserBriefDto> getUserAssignedToSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        return schedule.getUserScheduleList().stream()
                .map(userSchedule -> UserBriefDto.fromEntity(userSchedule.getUser()))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("일정을 찾을 수 없습니다."));
        if(requestDto.hasTitle())
            schedule.setTitle(requestDto.getTitle());

        if (requestDto.hasContent())
            schedule.setContent(requestDto.getContent());

        Schedule updateSchedule = scheduleRepository.save(schedule);
        return ScheduleResponseDto.fromEntity(updateSchedule);
    }
    @Transactional
    public ScheduleResponseDto assignUserToSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        boolean isAlreadyAssigned = schedule.getUserScheduleList().stream()
                .anyMatch(userSchedule -> userSchedule.getUser().getId().equals(userId));

        if (!isAlreadyAssigned){
            schedule.addUser(user);
        }

        return ScheduleResponseDto.fromEntity(schedule);
    }
    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        scheduleRepository.delete(schedule);
    }

    @Transactional
    public ScheduleResponseDto removeUserFromSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없스니다."));

        boolean userRemoved = schedule.getUserScheduleList().removeIf(userSchedule -> userSchedule.getUser().getId().equals(userId));

        if (!userRemoved)
            throw new RuntimeException("해당 일정에 연결된 사용자를 찾을 수 없습니다.");

        Schedule updateSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.fromEntity(updateSchedule);
    }
}
