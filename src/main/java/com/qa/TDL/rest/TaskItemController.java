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

import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.service.TaskItemService;

@RestController
@CrossOrigin
@RequestMapping("/taskItem")
public class TaskItemController {
	
	private TaskItemService service;

    @Autowired
    public TaskItemController(TaskItemService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskItemDTO> create(@RequestBody TaskItem taskItem) {
    	TaskItemDTO created = this.service.create(taskItem);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // readAll
    @GetMapping("/readAll")
    public ResponseEntity<List<TaskItemDTO>> readAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    // readById
    @GetMapping("/read/{taskItemId}")
    public ResponseEntity<TaskItemDTO> read(@PathVariable Long taskItemId) {
        return ResponseEntity.ok(this.service.read(taskItemId));
    }

    // update
    @PutMapping("/update/{taskItemId}")
    public ResponseEntity<TaskItemDTO> update(@PathVariable Long taskItemId, @RequestBody TaskItemDTO taskItemDTO) {
    	TaskItemDTO updated = this.service.update(taskItemDTO, taskItemId);
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    // delete
    @DeleteMapping("/delete/{taskItemId}")
    public ResponseEntity<TaskItemDTO> delete(@PathVariable Long taskItemId) {
        return this.service.delete(taskItemId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
