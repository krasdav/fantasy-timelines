package com.github.fantasytimelines.repository;

import com.github.fantasytimelines.model.EventAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventAttributeRepository extends JpaRepository<EventAttribute,Long> {
}
