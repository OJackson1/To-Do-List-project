package com.qa.TDL.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.exception.TaskNotFoundException;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.persistence.repository.TaskRepository;
import com.qa.TDL.utils.TDLBeanUtils;


@Service
public class TaskService {
	
	private TaskRepository repository;

    private ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepository repository, ModelMapper mapper) {
    	super();
        this.repository = repository;
        this.mapper = mapper;
    }

    private TaskDTO mapToDTO(Task task) {
        return this.mapper.map(task, TaskDTO.class);
    }

    public TaskDTO create(Task task) {
        Task created = this.repository.save(task);
        return this.mapToDTO(created);
    }

    public List<TaskDTO> readAll() {
    	return this.repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TaskDTO read(Long taskId) {
        Task found = this.repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        return this.mapToDTO(found);
    }

    public TaskDTO update(TaskDTO taskDTO, Long taskId) {
        Task toUpdate = this.repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        TDLBeanUtils.mergeNotNull(taskDTO, toUpdate);
        return this.mapToDTO(this.repository.save(toUpdate));
    }

    public boolean delete(Long taskId) {
        if (!this.repository.existsById(taskId)) {
            throw new TaskNotFoundException();
        }
        this.repository.deleteById(taskId);
        return !this.repository.existsById(taskId);
    }

}
