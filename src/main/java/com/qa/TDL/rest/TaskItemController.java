package com.qa.TDL.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/taskItem")
public class TaskItemController {
	
	private TaskItemService service;

    // Constructor Autowiring:
    //
    // Spring wires the Controller up to the Service at the moment the Controller is
    // created,
    // so, if the autowiring fails, then our Controller object never gets created!
    // This causes fewer exceptions - if we want to make sure our autowiring has
    // worked,
    // all we need to do is check if the Controller exists!
    @Autowired
    public TaskItemController(TaskItemService service) {
        super();
        this.service = service;
    }

    // create
//    @PostMapping("/create")
//    public ResponseEntity<GuitaristDTO> create(@RequestBody GuitaristDTO guitaristDTO) {
//        return new ResponseEntity<>(this.service.create(guitaristDTO), HttpStatus.CREATED);
//    }

    @PostMapping("/create")
    public ResponseEntity<TaskItemDTO> create(@RequestBody TaskItem taskItem) {
    	TaskItemDTO created = this.service.create(taskItem);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // readAll
    @GetMapping("/read")
    public ResponseEntity<List<TaskItemDTO>> read() {
        return ResponseEntity.ok(this.service.read());
    }

    // readById
    @GetMapping("/read/{id}")
    public ResponseEntity<TaskItemDTO> read(@PathVariable Long taskItemId) {
        return ResponseEntity.ok(this.service.read(taskItemId));
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskItemDTO> update(@PathVariable Long taskItemId, @RequestBody TaskItemDTO taskItemDTO) {
        return new ResponseEntity<>(this.service.update(taskItemDTO, taskItemId), HttpStatus.ACCEPTED);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskItemDTO> delete(@PathVariable Long taskItemId) {
        // Ternary Statements (If/Else):
        //
        // return the boolean result of the delete function
        // UNLESS the HTTP status returned is 204, in which case throw HTTP status 500
        return this.service.delete(taskItemId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 204
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<List<TaskItemDTO>> findByNameJPQL(@PathVariable String name) {
        return ResponseEntity.ok(this.service.findByNameJPQL(name));
    }

    @GetMapping("/searchType/{tasks}")
    public ResponseEntity<List<TaskItemDTO>> findByTypeJPQL(@PathVariable String tasks) {
        return ResponseEntity.ok(this.service.findByTypeJPQL(tasks));
    }

}
