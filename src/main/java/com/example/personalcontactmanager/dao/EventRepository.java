package com.example.personalcontactmanager.dao;

import com.example.personalcontactmanager.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for managing Event entities.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Finds events by user ID.
     *
     * @param userId the ID of the user
     * @return a list of events
     */
    List<Event> findByUserId(int userId);
}

