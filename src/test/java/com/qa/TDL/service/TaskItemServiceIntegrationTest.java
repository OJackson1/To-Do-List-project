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
import com.qa.TDL.service.TaskItemService;

@SpringBootTest
public class TaskItemServiceIntegrationTest {
	
    @Autowired
    private TaskItemService service;

    @Autowired
    private TaskItemRepository repo;

    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;

    @Autowired
    private ModelMapper modelMapper;

    private TaskItemDTO mapToDTO(TaskItem taskitem) {
        return this.modelMapper.map(taskitem, TaskItemDTO.class);
    }

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTaskItem = new TaskItem("Owen Jackson", "Shopping");
        this.testTaskItemWithId = this.repo.save(this.testTaskItem);
    }

    @Test
    void testCreate() {
        assertThat(this.mapToDTO(this.testTaskItemWithId))
            .isEqualTo(this.service.create(testTaskItem));
    }

    @Test
    void testRead() {
        assertThat(this.service.read(this.testTaskItemWithId.getTaskItemId()))
            .isEqualTo(this.mapToDTO(this.testTaskItemWithId));
    }

    @Test
    void testReadAll() {
        assertThat(this.service.read())
            .isEqualTo(Stream.of(this.mapToDTO(testTaskItemWithId)).collect(Collectors.toList()));
    }

    @Test
    void testUpdate() {
    	TaskItemDTO newTaskItem = new TaskItemDTO(null, "Loo reed", "Fitness");
    	TaskItemDTO updatedTaskItem = new TaskItemDTO(this.testTaskItemWithId.getTaskItemId(), newTaskItem.getName(), newTaskItem.getTask());

        assertThat(this.service.update(newTaskItem, this.testTaskItemWithId.getTaskItemId()))
            .isEqualTo(updatedTaskItem);
    }

    @Test
    void testDelete() {
        assertThat(this.service.delete(this.testTaskItemWithId.getTaskItemId()))
            .isTrue();
    }

}
