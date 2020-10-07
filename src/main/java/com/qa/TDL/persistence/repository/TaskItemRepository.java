package com.qa.TDL.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.TDL.persistence.domain.TaskItem;

public interface TaskItemRepository extends JpaRepository<TaskItem, Long>  {
	
	@Query("SELECT p FROM TaskItem p WHERE p.name = ?1")
    Optional<TaskItem> findByNameJPQL(String name);

    @Query("SELECT p FROM TaskItem p WHERE p.type = ?1")
    Optional<TaskItem> findByTypeJPQL(String task);

}
