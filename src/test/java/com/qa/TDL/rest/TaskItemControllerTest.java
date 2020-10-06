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
 
    private List<TaskItem> taskitemList;
    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;
    private TaskItemDTO taskitemDTO;
    
    private final String name = "Owen Jackson";
    private final String task= "Shopping";
    private final Long taskItemId = 1L;

    

    @BeforeEach
    void init() {
        this.taskitemList = new ArrayList<>();
        this.testTaskItem = new TaskItem(name,task);
        this.testTaskItemWithId = new TaskItem(testTaskItem.getName(), testTaskItem.getTask());
        this.testTaskItemWithId.setTaskItemId(taskItemId);
        this.taskitemList.add(testTaskItemWithId);
        this.taskitemDTO = this.mapToDTO(testTaskItemWithId);
    }

    @Test
    void createTest() {
        when(this.service.create(testTaskItem))
            .thenReturn(this.taskitemDTO);
        
        assertThat(new ResponseEntity<TaskItemDTO>(this.taskitemDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTaskItem));
        
        verify(this.service, times(1))
            .create(this.testTaskItem);
    }

    @Test
    void readTest() {
        when(this.service.read(this.taskItemId))
            .thenReturn(this.taskitemDTO);
        
        assertThat(new ResponseEntity<TaskItemDTO>(this.taskitemDTO, HttpStatus.OK))
                .isEqualTo(this.controller.read(this.taskItemId));
        
        verify(this.service, times(1))
            .read(this.taskItemId);
    }

    @Test
    void readAllTest() {
        when(service.read())
            .thenReturn(this.taskitemList
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.read().getBody()
                .isEmpty()).isFalse();
        
        verify(service, times(1))
            .read();
    }

    @Test
    void updateTest() {
        // given
    	TaskItemDTO newTaskItem = new TaskItemDTO(null, "Loo reed","Fitness");
    	TaskItemDTO updatedTaskItem = new TaskItemDTO(this.taskItemId, newTaskItem.getName(),
                newTaskItem.getTask());

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
