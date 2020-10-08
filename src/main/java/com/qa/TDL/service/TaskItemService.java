package com.qa.TDL.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.exception.TaskItemNotFoundException;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.persistence.repository.TaskItemRepository;
import com.qa.TDL.utils.TDLBeanUtils;



@Service
public class TaskItemService {
	
	 private TaskItemRepository repository;

	    private ModelMapper mapper;

	    @Autowired
	    public TaskItemService(TaskItemRepository repository, ModelMapper model) {
	    	super();
	        this.repository = repository;
	        this.mapper = model;
	    }

	    private TaskItemDTO mapToDTO(TaskItem taskItem) {
	        return this.mapper.map(taskItem, TaskItemDTO.class);
	    }

	    public TaskItemDTO create(TaskItem taskItem) {
	    	TaskItem created = this.repository.save(taskItem);
	    	return this.mapToDTO(created);
	    }

	    // readAll
	    public List<TaskItemDTO> readAll() {
	    	return this.repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	    }

	    // readById
	    public TaskItemDTO read(Long taskItemId) {
	    	TaskItem found = this.repository.findById(taskItemId).orElseThrow(TaskItemNotFoundException::new);
	        return this.mapToDTO(found);
	    }

	    // update
	    public TaskItemDTO update(TaskItemDTO taskItemDTO, Long taskItemId) {
	        TaskItem toUpdate = this.repository.findById(taskItemId).orElseThrow(TaskItemNotFoundException::new);
	        TDLBeanUtils.mergeNotNull(taskItemDTO, toUpdate);
	        return this.mapToDTO(this.repository.save(toUpdate));
	    }

	    // delete
	    public boolean delete(Long taskItemId) {
	        if (!this.repository.existsById(taskItemId)) {
	            throw new TaskItemNotFoundException();
	        } else {
	        this.repository.deleteById(taskItemId);
	        }
	        return !this.repository.existsById(taskItemId);
	    }

}
