package com.webstrdy00.upgrade_schedule.repository;

import com.webstrdy00.upgrade_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u LEFT JOIN fetch u.userScheduleList us LEFT JOIN FETCH us.schedule WHERE u.id = :userId")
    Optional<User> findByIdWithSchedules(Long userId);
}
