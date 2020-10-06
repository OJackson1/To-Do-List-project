package com.qa.TDL.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.TDL.dto.TaskDTO;
import com.qa.TDL.persistence.domain.Task;
import com.qa.TDL.persistence.repository.TaskRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntergrationTest {
	
	@Autowired
    private MockMvc mock;

    @Autowired
    private TaskRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private Task testTask;
    private Task testTaskWithId;
    
    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTask = new Task("Shopping");
        this.testTaskWithId = this.repo.save(this.testTask);
        this.id = this.testTaskWithId.getTaskId();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/task/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testTask))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(testTaskWithId)));
    }

    @Test
    void testRead() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/task/read/" + this.id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.testTask)));
    }

    @Test
    void testReadAll() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(this.testTaskWithId);

        String content = this.mock
            .perform(request(HttpMethod.GET, "/task/read")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(taskList), content);
    }

    @Test
    void testUpdate() throws Exception {
        Task newTask = new Task("Shopping");
        Task updatedTask = new Task(newTask.getName());
        updatedTask.setTaskId(this.id);

        String result = this.mock
            .perform(request(HttpMethod.PUT, "/task/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newTask)))
            .andExpect(status().isAccepted())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedTask)), result);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/task/delete/" + this.id))
            .andExpect(status().isNoContent());
    }


}
