package com.github.fantasytimelines.repository;

import com.github.fantasytimelines.model.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline,Long> {
}
