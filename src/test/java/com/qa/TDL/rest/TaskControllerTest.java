package com.qa.TDL.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.service.TaskService;

@SpringBootTest
public class TaskControllerTest {
	
	@Autowired
	private TaskController controller;
	
	@Autowired
	private ModelMapper mapper;
	
	@MockBean
    private TaskService service;

    private List<Task> tasks;
    private Task testTask;
    private Task testTaskWithID;
    private TaskDTO taskDTO;
    private final Long taskId = 1L;

    private TaskDTO mapToDTO(Task task) {
        return this.mapper.map(task, TaskDTO.class);
    }

    @BeforeEach
    void init() {
        this.tasks = new ArrayList<>();
        this.testTask = new Task("Shopping");
        this.testTaskWithID = new Task(testTask.getName());
        this.testTaskWithID.setTaskId(taskId);
        this.tasks.add(testTaskWithID);
        this.taskDTO = this.mapToDTO(testTaskWithID);
    }

    @Test
    void createTest() {
        when(this.service.create(testTask))
            .thenReturn(this.taskDTO);
        
        assertThat(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTask));
        
        verify(this.service, times(1))
            .create(this.testTask);
    }

    @Test
    void readOneTest() {
        when(this.service.read(this.taskId))
            .thenReturn(this.taskDTO);
        
        assertThat(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.OK))
                .isEqualTo(this.controller.read(this.taskId));
        
        verify(this.service, times(1))
            .read(this.taskId);
    }

    @Test
    void readAllTest() {
        when(service.readAll())
            .thenReturn(this.tasks
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.readAll().getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1))
            .readAll();
    }

    @Test
    void updateTest() {
        TaskDTO newTask= new TaskDTO(null, "Fitness", null);
        TaskDTO updatedTask= new TaskDTO(this.taskId, newTask.getName(), null);

        when(this.service.update(newTask, this.taskId))
            .thenReturn(updatedTask);
        
        assertThat(new ResponseEntity<TaskDTO>(updatedTask, HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(this.taskId, newTask));
        
        verify(this.service, times(1))
            .update(newTask, this.taskId);
    }
    
    @Test
    void deleteTest() {
        this.controller.delete(taskId);

        verify(this.service, times(1))
            .delete(taskId);
    }

}
