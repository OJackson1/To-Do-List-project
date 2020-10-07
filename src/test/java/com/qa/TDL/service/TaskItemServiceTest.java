package com.qa.TDL.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.persistence.repository.TaskItemRepository;

@SpringBootTest
public class TaskItemServiceTest {
	
	@Autowired
    private TaskItemService service;

    @MockBean
    private TaskItemRepository repository;

    @MockBean
    private ModelMapper modelMapper;

    private List<TaskItem> taskItemList;
    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;
    private TaskItem emptyTaskItem;
    private TaskItemDTO taskItemDTO;

    final Long taskItemId = 1L;
    final String testName = "Eggs";

    private TaskItemDTO mapToDTO(TaskItem taskItem) {
        return this.modelMapper.map(taskItem, TaskItemDTO.class);
    }

    @BeforeEach
    void init() {
        this.taskItemList = new ArrayList<>();
        this.taskItemList.add(testTaskItem);
        this.testTaskItem = new TaskItem(testName);
        this.testTaskItemWithId = new TaskItem(testTaskItem.getName());
        this.emptyTaskItem = new TaskItem();
        this.testTaskItemWithId.setTaskItemId(taskItemId);
        this.taskItemDTO = new ModelMapper().map(testTaskItemWithId, TaskItemDTO.class);
    }

    @Test
    void createTest() {
        when(this.modelMapper.map(mapToDTO(testTaskItem), TaskItem.class))
        .thenReturn(testTaskItem);

        when(this.repository.save(testTaskItem))
        .thenReturn(testTaskItemWithId);

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class))
        .thenReturn(taskItemDTO);

        TaskItemDTO expected = this.taskItemDTO;
        TaskItemDTO actual = this.service.create(this.testTaskItem);
        assertThat(expected).isEqualTo(actual);

        verify(this.repository, times(1))
        .save(this.testTaskItem);
    }

    @Test
    void readTest() {
        when(this.repository.findById(this.taskItemId))
        .thenReturn(Optional.of(this.testTaskItemWithId));

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class))
        .thenReturn(taskItemDTO);

        assertThat(this.taskItemDTO).isEqualTo(this.service
        .read(this.taskItemId));

        verify(this.repository, times(1))
        .findById(this.taskItemId);
    }

    @Test
    void readAllTest() {
        when(repository.findAll()).thenReturn(this.taskItemList);

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class))
        .thenReturn(taskItemDTO);

        assertThat(this.service.readAll()
        .isEmpty()).isFalse();

        verify(repository, times(1))
        .findAll();
    }

    @Test
    void updateTest() {
    	 TaskItem taskItem = new TaskItem("Eggs");
         taskItem.setTaskItemId(this.taskItemId);

         TaskItemDTO taskItemDTO = new TaskItemDTO(null, "Eggs");

         TaskItem updatedTaskItem = new TaskItem(taskItemDTO.getName());
         updatedTaskItem.setTaskItemId(this.taskItemId);

         TaskItemDTO updatedTaskItemDTO = new TaskItemDTO(this
        		 .taskItemId, updatedTaskItem.getName());

         when(this.repository.findById(this.taskItemId))
         .thenReturn(Optional.of(taskItem));

         when(this.repository.save(taskItem))
         .thenReturn(updatedTaskItem);

         when(this.modelMapper.map(updatedTaskItem, TaskItemDTO.class)).thenReturn(updatedTaskItemDTO);

         assertThat(updatedTaskItemDTO).isEqualTo(this
        		 .service.update(taskItemDTO, this.taskItemId));

         verify(this.repository, times(1))
         .findById(1L);
         verify(this.repository, times(1))
         .save(updatedTaskItem);
     }


    @Test
    void deleteTest() {
        when(this.repository.existsById(taskItemId)).thenReturn(true, false);

        assertThat(this.service.delete(taskItemId))
        .isTrue();

        verify(this.repository, times(1))
        .deleteById(taskItemId);

        verify(this.repository, times(2))
        .existsById(taskItemId);
    }

}
