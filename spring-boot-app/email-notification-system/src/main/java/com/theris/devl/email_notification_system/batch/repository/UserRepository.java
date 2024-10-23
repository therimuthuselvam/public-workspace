package com.theris.devl.email_notification_system.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theris.devl.email_notification_system.batch.entity.AppUsers;

@Repository
public interface UserRepository extends JpaRepository<AppUsers, Long> {
    List<AppUsers> findByNotificationsEnabledTrue();
}
