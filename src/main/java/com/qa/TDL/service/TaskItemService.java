package com.qa.TDL.service;

import java.util.List;
import java.util.Optional;
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
	
	 private TaskItemRepository repo;

	    private ModelMapper mapper;

	    @Autowired
	    public TaskItemService(TaskItemRepository repo, ModelMapper mapper) {
	        this.repo = repo;
	        this.mapper = mapper;
	    }

	    private TaskItemDTO mapToDTO(TaskItem taskItem) {
	        return this.mapper.map(taskItem, TaskItemDTO.class);
	    }

//	    private Guitarist mapFromDTO(GuitaristDTO guitaristDTO) {
//	        return this.mapper.map(guitaristDTO, Guitarist.class);
//	    }

	    // create
//	    public GuitaristDTO create(GuitaristDTO guitaristDTO) {
//	        Guitarist toSave = this.mapFromDTO(guitaristDTO);
//	        Guitarist saved = this.repo.save(toSave);
//	        return this.mapToDTO(saved);
//	    }

	    public TaskItemDTO create(TaskItem taskItem) {
	    	TaskItem created = this.repo.save(taskItem);
	    	TaskItemDTO mapped = this.mapToDTO(created);
	        return mapped;
	    }

	    // readAll
	    public List<TaskItemDTO> read() {
	        List<TaskItem> found = this.repo.findAll();
	        List<TaskItemDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
	        return streamed;
	    }

	    // readById
	    public TaskItemDTO read(Long taskItemId) {
	    	TaskItem found = this.repo.findById(taskItemId).orElseThrow(TaskItemNotFoundException::new);
	        return this.mapToDTO(found);
	    }

	    // update
	    public TaskItemDTO update(TaskItemDTO taskItemDTO, Long taskItemId) {
	        TaskItem toUpdate = this.repo.findById(taskItemId).orElseThrow(TaskItemNotFoundException::new);
	        TDLBeanUtils.mergeNotNull(taskItemDTO, toUpdate);
	        return this.mapToDTO(this.repo.save(toUpdate));
	    }

	    // delete
	    public boolean delete(Long taskItemId) {
	        if (!this.repo.existsById(taskItemId)) {
	            throw new TaskItemNotFoundException();
	        }
	        this.repo.deleteById(taskItemId);
	        return !this.repo.existsById(taskItemId);
	    }

	    public List<TaskItemDTO> findByNameJPQL(String name) {
	        Optional<TaskItem> found = this.repo.findByNameJPQL(name);
	        List<TaskItemDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
	        return streamed;
	    }


	    public List<TaskItemDTO> findByTypeJPQL(String tasks) {
	        Optional<TaskItem> found = this.repo.findByTypeJPQL(tasks);
	        List<TaskItemDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
	        return streamed;
	    }

}
