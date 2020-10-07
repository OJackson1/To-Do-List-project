package com.qa.TDL.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.persistence.repository.TaskRepository;

@SpringBootTest
public class TaskServiceTest {
	
	@Autowired
    private TaskService service;

    @MockBean
    private TaskRepository repository;

    @MockBean
    private ModelMapper modelMapper;

    private List<Task> tasks;
    private Task testTask;
    private Task testTaskWithId;
    private TaskDTO taskDTO;

    final Long taskId = 1L;
    final String testName = "Eggs";

    @BeforeEach
    void init() {
        this.tasks= new ArrayList<>();
        this.testTask= new Task(testName);
        this.tasks.add(testTask);
        this.testTaskWithId = new Task(testTask.getName());
        this.testTaskWithId.setTaskId(taskId);
        this.taskDTO = modelMapper.map(testTaskWithId, TaskDTO.class);
    }

    @Test
    void createTest() {

        when(this.repository.save(this.testTask)).thenReturn(this.testTaskWithId);

        when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class)).thenReturn(this.taskDTO);

        TaskDTO expected = this.taskDTO;
        TaskDTO actual = this.service.create(this.testTask);
        assertThat(expected).isEqualTo(actual);

        verify(this.repository, times(1)).save(this.testTask);
    }

    @Test
    void readOneTest() {

        when(this.repository.findById(this.taskId)).thenReturn(Optional.of(this.testTaskWithId));

        when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class)).thenReturn(this.taskDTO);

        assertThat(this.taskDTO).isEqualTo(this.service.read(this.taskId));

        verify(this.repository, times(1)).findById(this.taskId);
    }

    @Test
    void readAllTest() {

        when(this.repository.findAll()).thenReturn(this.tasks);

        when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class)).thenReturn(this.taskDTO);

        assertThat(this.service.readAll().isEmpty()).isFalse();

        verify(this.repository, times(1)).findAll();
    }

    @Test
    void updateTest() {
        Task task = new Task("Shopping");
        task.setTaskId(this.taskId);

        TaskDTO taskDTO = new TaskDTO(null, "Shopping", null);

        Task updatedTask = new Task(taskDTO.getName());
        updatedTask.setTaskId(this.taskId);

        TaskDTO updatedTaskDTO = new TaskDTO(this.taskId, updatedTask.getName(), null);

        when(this.repository.findById(this.taskId)).thenReturn(Optional.of(task));

        when(this.repository.save(task)).thenReturn(updatedTask);

        when(this.modelMapper.map(updatedTask, TaskDTO.class)).thenReturn(updatedTaskDTO);

        assertThat(updatedTaskDTO).isEqualTo(this.service.update(taskDTO, this.taskId));

        verify(this.repository, times(1)).findById(1L);
        verify(this.repository, times(1)).save(updatedTask);
    }

    @Test
    void deleteTest() {

        when(this.repository.existsById(taskId)).thenReturn(true, false);

        assertThat(this.service.delete(taskId)).isTrue();

        verify(this.repository, times(1)).deleteById(taskId);

        verify(this.repository, times(2)).existsById(taskId);
    }

}
