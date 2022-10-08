package com.github.fantasytimelines.repository;

import com.github.fantasytimelines.model.TimelineAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineAttributeRepository extends JpaRepository<TimelineAttribute,Long> {
}
