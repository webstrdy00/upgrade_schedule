package com.webstrdy00.upgrade_schedule.service;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.ScheduleResponseDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserRequestDto;
import com.webstrdy00.upgrade_schedule.dto.userDto.UserResponseDto;
import com.webstrdy00.upgrade_schedule.entity.Schedule;
import com.webstrdy00.upgrade_schedule.entity.User;
import com.webstrdy00.upgrade_schedule.repository.ScheduleRepository;
import com.webstrdy00.upgrade_schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public UserService(UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = requestDto.toEntity();
        User savedUser = userRepository.save(user);
        return UserResponseDto.fromEntity(savedUser);
    }
    @Transactional
    public UserResponseDto assignScheduleToUser(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        boolean isAlreadyAssigned = user.getUserScheduleList().stream()
                .anyMatch(userSchedule -> userSchedule.getSchedule().getId().equals(scheduleId));

        if (!isAlreadyAssigned){
            user.addSchedule(schedule);
        }

        return UserResponseDto.fromEntity(user);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        return UserResponseDto.fromEntity(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponseDto> getUserSchedules(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        return user.getUserScheduleList().stream()
                .map(userSchedule -> ScheduleResponseDto.fromEntity(userSchedule.getSchedule()))
                .collect(Collectors.toList());
    }
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저을 찾을 수 없습니다."));
        if (requestDto.hasUsername())
            user.setUsername(requestDto.getUsername());
        if (requestDto.hasEmail())
            user.setEmail(requestDto.getEmail());

        User updateUser = userRepository.save(user);
        return UserResponseDto.fromEntity(updateUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("유저을 찾을 수 없습니다."));
        userRepository.delete(user);
    }

    @Transactional
    public UserResponseDto removeScheduleFromUser(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        user.getUserScheduleList().removeIf(userSchedule -> userSchedule.getSchedule().getId().equals(scheduleId));
        User updateUser = userRepository.save(user);

        return UserResponseDto.fromEntity(updateUser);
    }

}
