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
    private TaskRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private String testName;
    private Long taskId;
    private Task testTask;
    private TaskDTO taskDTO;
    private Task testTaskWithId;
    
    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }

    @BeforeEach
    void init() {
    	this.repository.deleteAll();
        this.testTask = new Task("Shopping");
        this.testTaskWithId = this.repository.save(this.testTask);
        this.taskDTO = this.mapToDTO(testTaskWithId);
        this.taskId = this.testTaskWithId.getTaskId();
        this.testName = this.testTaskWithId.getName();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/task/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testTask))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(taskDTO)));
    }

    @Test
    void testRead() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/task/read/" + this.taskId)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.taskDTO)));
    }

    @Test
    void testReadAll() throws Exception {
        List<TaskDTO> taskList = new ArrayList<>();
        taskList.add(this.taskDTO);

        String content = this.mock
            .perform(request(HttpMethod.GET, "/task/readAll")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(taskList), content);
    }

    @Test
    void testUpdate() throws Exception {
        Task newTask = new Task(null,"Shopping",null);
        Task updatedTask = new Task(newTask.getName());
        updatedTask.setTaskId(this.taskId);

        String result = this.mock
            .perform(request(HttpMethod.PUT, "/task/update/" + this.taskId)
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
            .perform(request(HttpMethod.DELETE, "/task/delete/" + this.taskId))
            .andExpect(status().isNoContent());
    }


}
