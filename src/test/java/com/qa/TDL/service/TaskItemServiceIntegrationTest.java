package com.qa.TDL.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.persistence.repository.TaskItemRepository;

@SpringBootTest
public class TaskItemServiceIntegrationTest {
	
    @Autowired
    private TaskItemService service;

    @Autowired
    private TaskItemRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private TaskItemDTO mapToDTO(TaskItem taskitem) {
        return this.modelMapper.map(taskitem, TaskItemDTO.class);
    }
    
    private TaskItem testTaskItem;
    private TaskItemDTO testTaskItemDTO;
    private TaskItem testTaskItemWithId;
    
    private Long taskItemId;
    private final String name = "Eggs";

    @BeforeEach
    void init() {
        this.repository.deleteAll();
        this.testTaskItem= new TaskItem(name);
        this.testTaskItemWithId = this.repository.save(this.testTaskItem);
        this.testTaskItemDTO = this.mapToDTO(testTaskItemWithId);
        this.taskItemId = this.testTaskItemWithId.getTaskItemId();
    }

    @Test
    void testCreate() {
        assertThat(this.testTaskItemDTO)
        .isEqualTo(this.service
        .create(testTaskItem));
    }

    @Test
    void testRead() {
        assertThat(this.testTaskItemDTO)
            .isEqualTo(this.service
            .read(this.taskItemId));
    }

    @Test
    void testReadAll() {
        assertThat(this.service.readAll())
            .isEqualTo(Stream.of(this
            .testTaskItemDTO).collect(Collectors
            .toList()));
    }

    @Test
    void testUpdate() {
    	TaskItemDTO newTaskItem = new TaskItemDTO(null, "Shopping");
    	TaskItemDTO updatedTaskItem = new TaskItemDTO(this.taskItemId,newTaskItem.getName());

        assertThat(this.service.update(newTaskItem, this
        	.testTaskItemWithId.getTaskItemId()))
            .isEqualTo(updatedTaskItem);
    }

    @Test
    void testDelete() {
        assertThat(this.service
        	.delete(this.taskItemId))
            .isTrue();
    }

}
