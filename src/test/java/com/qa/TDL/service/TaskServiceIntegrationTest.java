package com.qa.TDL.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.persistence.repository.TaskRepository;

@SpringBootTest
public class TaskServiceIntegrationTest {
	
	 @Autowired
	    private TaskService service;

	    @Autowired
	    private TaskRepository repository;

	    @MockBean
	    private ModelMapper modelMapper;
	    
	    private final List<TaskItemDTO> taskItems = new ArrayList();

	    private Task testTask;
	    private Task testTaskWithId;
	    private TaskDTO testTaskDTO;

	    private Long taskId;
	    private final String name = "Eggs";

	    @BeforeEach
	    void init() {
	        this.repository.deleteAll();
	        this.testTask = new Task(name);
	        this.testTaskWithId = this.repository.save(this.testTask);
	        this.testTaskDTO = modelMapper.map(testTaskWithId, TaskDTO.class);
	        this.taskId = this.testTaskWithId.getTaskId();
	    }

	    @Test
	    void testCreate() {
	    	
	    	when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class))
	    	.thenReturn(this.testTaskDTO);
	    	
	        assertThat(this.testTaskDTO)
	            .isEqualTo(this.service
	            .create(testTask));
	    }

	    @Test
	    void testRead() {
	    	
	    	when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class)).thenReturn(this.testTaskDTO);
	    	
	        assertThat(this.testTaskDTO)
	                .isEqualTo(this.service
	                .read(this.taskId));
	    }

	    @Test
	    void testReadAll() {
	    	
	    	when(this.modelMapper.map(this.testTaskWithId, TaskDTO.class))
	    	.thenReturn(this.testTaskDTO);
	    	
	        assertThat(this.service.readAll())
	                .isEqualTo(Stream.of(this.testTaskDTO)
	                .collect(Collectors.toList()));
	    }

	    @Test
	    void testUpdate() {
	    	
	        TaskDTO oldDTO = new TaskDTO(null, "Fitness", this.taskItems);
	        TaskDTO newDTO = new TaskDTO(this.taskId, oldDTO.getName(), oldDTO.getTaskItem());
	        

	        assertThat(newDTO)
	            .isEqualTo(this
	            .service.update(oldDTO, this.taskId));
	    }

	    @Test
	    void testDelete() {
	        assertThat(this.service.
	        delete(this.taskId)).
	        isTrue();
	    }
	
	

}
