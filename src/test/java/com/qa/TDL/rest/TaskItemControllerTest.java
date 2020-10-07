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

import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.service.TaskItemService;

@SpringBootTest
public class TaskItemControllerTest {
	
	@Autowired
    private TaskItemController controller;
    
    @MockBean
    private TaskItemService service;
    
    @Autowired
    private ModelMapper modelMapper;
    
    private TaskItemDTO mapToDTO(TaskItem taskItem) {
        return this.modelMapper.map(taskItem, TaskItemDTO.class);
    }
 
    private List<TaskItem> taskItemList;
    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;
    private TaskItemDTO taskItemDTO;
    
    private final String name = "Owen";
    private final Long taskItemId = 1L;

    

    @BeforeEach
    void init() {
        this.taskItemList = new ArrayList<>();
        this.testTaskItem = new TaskItem(name);
        this.testTaskItemWithId = new TaskItem(testTaskItem.getName());
        this.testTaskItemWithId.setTaskItemId(taskItemId);
        this.taskItemList.add(testTaskItemWithId);
        this.taskItemDTO = this.mapToDTO(testTaskItemWithId);
    }

    @Test
    void createTest() {
        when(this.service.create(testTaskItem))
            .thenReturn(this.taskItemDTO);
        
        assertThat(new ResponseEntity<TaskItemDTO>(this.taskItemDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTaskItem));
        
        verify(this.service, times(1))
            .create(this.testTaskItem);
    }

    @Test
    void readTest() {
        when(this.service.read(this.taskItemId))
            .thenReturn(this.taskItemDTO);
        
        assertThat(new ResponseEntity<TaskItemDTO>(this.taskItemDTO, HttpStatus.OK))
                .isEqualTo(this.controller.read(this.taskItemId));
        
        verify(this.service, times(1))
            .read(this.taskItemId);
    }

    @Test
    void readAllTest() {
        when(service.readAll())
            .thenReturn(this.taskItemList
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.readAll().getBody()
                .isEmpty()).isFalse();
        
        verify(service, times(1))
            .readAll();
    }

    @Test
    void updateTest() {
    	TaskItemDTO newTaskItem = new TaskItemDTO(null, "Eggs");
    	TaskItemDTO updatedTaskItem = new TaskItemDTO(this.taskItemId, newTaskItem.getName());

        when(this.service.update(newTaskItem, this.taskItemId))
            .thenReturn(updatedTaskItem);
        
        assertThat(new ResponseEntity<TaskItemDTO>(updatedTaskItem, HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(this.taskItemId, newTaskItem));
        
        verify(this.service, times(1))
            .update(newTaskItem, this.taskItemId);
    }
    
    @Test
    void deleteTest() {
        this.controller.delete(taskItemId);

        verify(this.service, times(1))
            .delete(taskItemId);
    }

}
