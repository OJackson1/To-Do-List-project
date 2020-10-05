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
	
	private TaskRepository repo;

    private ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private TaskDTO mapToDTO(Task task) {
        return this.mapper.map(task, TaskDTO.class);
    }

    private Task mapFromDTO(TaskDTO taskDTO) {
        return this.mapper.map(taskDTO, Task.class);
    }

//    public BandDTO create(BandDTO bandDTO) {
//        Band toSave = this.mapFromDTO(bandDTO);
//        Band saved = this.repo.save(toSave);
//        return this.mapToDTO(saved);
//    }

    public TaskDTO create(Task task) {
        Task created = this.repo.save(task);
        TaskDTO mapped = this.mapToDTO(created);
        return mapped;
    }

    public List<TaskDTO> read() {
        List<Task> found = this.repo.findAll();
        List<TaskDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
        return streamed;
    }

    public TaskDTO read(Long taskId) {
        Task found = this.repo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        return this.mapToDTO(found);
    }

    public TaskDTO update(TaskDTO taskDTO, Long taskId) {
        Task toUpdate = this.repo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        TDLBeanUtils.mergeNotNull(taskDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long taskId) {
        if (!this.repo.existsById(taskId)) {
            throw new TaskNotFoundException();
        }
        this.repo.deleteById(taskId);
        return !this.repo.existsById(taskId);
    }

}
