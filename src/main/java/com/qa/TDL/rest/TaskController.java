package com.qa.TDL.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
	
	 private TaskService service;

	    @Autowired
	    public TaskController(TaskService service) {
	        super();
	        this.service = service;
	    }

	    @PostMapping("/create")
	    public ResponseEntity<TaskDTO> create(@RequestBody Task task) {
	        TaskDTO created = this.service.create(task);
	        return new ResponseEntity<>(created, HttpStatus.CREATED);
	    }

	    @GetMapping("/readAll")
	    public ResponseEntity<List<TaskDTO>> readAll() {
	        return ResponseEntity.ok(this.service.readAll());
	    }

	    @GetMapping("/read/{id}")
	    public ResponseEntity<TaskDTO> read(@PathVariable Long taskId) {
	        return ResponseEntity.ok(this.service.read(taskId));
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<TaskDTO> update(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
	    	TaskDTO updated = this.service.update(taskDTO, taskId);
	        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<TaskDTO> delete(@PathVariable Long taskId) {
	        return this.service.delete(taskId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
	                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
