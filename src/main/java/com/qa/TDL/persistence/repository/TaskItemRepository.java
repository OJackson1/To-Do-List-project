package com.qa.TDL.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.TDL.persistence.domain.TaskItem;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long>  {

}
