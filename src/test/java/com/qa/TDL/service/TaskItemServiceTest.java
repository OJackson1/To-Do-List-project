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
    private TaskItemRepository repo;

    @MockBean
    private ModelMapper modelMapper;

    private List<TaskItem> taskItemList;
    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;
    private TaskItem emptyTaskItem;
    private TaskItemDTO taskItemDTO;

    final Long taskItemId = 1L;
    final String testName = "Owen Jackson";
    final String testTask = "Shopping";

    private TaskItemDTO mapToDTO(TaskItem taskItem) {
        return this.modelMapper.map(taskItem, TaskItemDTO.class);
    }

//    @BeforeEach
//    void init() {
//        this.taskItemList = new ArrayList<>();
//        this.taskItemList.add(testTaskItem);
//        this.testTaskItem = new TaskItem("Owen Jackson","Shopping");
//        this.testTaskItemWithId = new TaskItem(testTaskItem.getName(),
//                testTaskItem.getTask());
//        this.emptyTaskItem = new TaskItem();
//        this.testTaskItemWithId.setTaskItemId(taskItemId);
//        this.taskItemDTO = new ModelMapper().map(testTaskItemWithId, TaskItemDTO.class);
//    }

    @Test
    void createTest() {
        when(this.modelMapper.map(mapToDTO(testTaskItem), TaskItem.class)).thenReturn(testTaskItem);

        when(this.repo.save(testTaskItem)).thenReturn(testTaskItemWithId);

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class)).thenReturn(taskItemDTO);

        assertThat(this.taskItemDTO).isEqualTo(this.service.create(testTaskItem));

        verify(this.repo, times(1)).save(this.testTaskItem);
    }

    @Test
    void readTest() {
        when(this.repo.findById(this.taskItemId)).thenReturn(Optional.of(this.testTaskItemWithId));

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class)).thenReturn(taskItemDTO);

        assertThat(this.taskItemDTO).isEqualTo(this.service.read(this.taskItemId));

        verify(this.repo, times(1)).findById(this.taskItemId);
    }

    @Test
    void readAllTest() {
        when(repo.findAll()).thenReturn(this.taskItemList);

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class)).thenReturn(taskItemDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(repo, times(1)).findAll();
    }

//    @Test
//    void updateTest() {
//        final long ID = 1L;
//
//        TaskItemDTO newTaskItem = new TaskItemDTO(null, "Owen Jackson", "Shopping");
//
//        TaskItem taskItem = new TaskItem("Loo Reed", "Fitness");
//        taskItem.setTaskItemId(ID);
//
//        TaskItem updatedTaskItem = new TaskItem(newTaskItem.getName(),
//                newTaskItem.getTask());
//        updatedTaskItem.setTaskItemId(ID);
//
//        TaskItemDTO updatedDTO = new TaskItemDTO(ID, updatedTaskItem.getName(),
//                updatedTaskItem.getTask());
//
//        when(this.repo.findById(this.taskItemId)).thenReturn(Optional.of(taskItem));
//
//        when(this.repo.save(updatedTaskItem)).thenReturn(updatedTaskItem);
//
//        when(this.modelMapper.map(updatedTaskItem, TaskItemDTO.class)).thenReturn(updatedDTO);
//
//        assertThat(updatedDTO).isEqualTo(this.service.update(newTaskItem, this.taskItemId));
//
//        verify(this.repo, times(1)).findById(1L);
//
//        verify(this.repo, times(1)).save(updatedTaskItem);
//    }

    @Test
    void deleteTest() {
        when(this.repo.existsById(taskItemId)).thenReturn(true, false);

        assertThat(this.service.delete(taskItemId)).isTrue();

        verify(this.repo, times(1)).deleteById(taskItemId);

        verify(this.repo, times(2)).existsById(taskItemId);
    }

    @Test
    void findByNameJPQLTest() {
        emptyTaskItem.setName(testName);

        when(this.repo.findByNameJPQL(this.testName)).thenReturn(Optional.of(emptyTaskItem));

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class)).thenReturn(taskItemDTO);

        assertThat(this.service.findByNameJPQL(this.testName).isEmpty()).isFalse();

        verify(this.repo, times(1)).findByNameJPQL(this.testName);
    }

    
    @Test
    void findByTypeJPQLTest() {
        emptyTaskItem.setTask(testTask);

        when(this.repo.findByTypeJPQL(this.testTask)).thenReturn(Optional.of(emptyTaskItem));

        when(this.modelMapper.map(testTaskItemWithId, TaskItemDTO.class)).thenReturn(taskItemDTO);

        assertThat(this.service.findByTypeJPQL(this.testTask).isEmpty()).isFalse();

        verify(this.repo, times(1)).findByTypeJPQL(this.testTask);
    }

}
