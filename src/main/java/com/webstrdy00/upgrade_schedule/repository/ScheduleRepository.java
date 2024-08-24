package com.webstrdy00.upgrade_schedule.repository;

import com.webstrdy00.upgrade_schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
