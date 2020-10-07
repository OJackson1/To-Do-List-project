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
import com.qa.TDL.dto.TaskItemDTO;
import com.qa.TDL.persistence.domain.TaskItem;
import com.qa.TDL.persistence.repository.TaskItemRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskItemControllerIntergrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private TaskItemRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskItem testTaskItem;
    private TaskItem testTaskItemWithId;
    private TaskItemDTO taskItemDTO;

    private Long taskItemId;
    private String testName;

    private TaskItemDTO mapToDTO(TaskItem taskItem) {
        return this.modelMapper.map(taskItem, TaskItemDTO.class);
    }

    @BeforeEach
    void init() {
        this.repository.deleteAll();

        this.testTaskItem = new TaskItem("Shopping");
        this.testTaskItemWithId = this.repository.save(this.testTaskItem);
        this.taskItemDTO = this.mapToDTO(testTaskItemWithId);

        this.taskItemId = this.testTaskItemWithId.getTaskItemId();
        this.testName = this.testTaskItemWithId.getName();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
                .perform(request(HttpMethod.POST, "/taskItem/create").contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testTaskItem))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(this.objectMapper.writeValueAsString(taskItemDTO)));
    }

    @Test
    void testRead() throws Exception {
        this.mock.perform(request(HttpMethod.GET, "/taskItem/read/" + this.taskItemId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(this.taskItemDTO)));
    }

    @Test
    void testReadAll() throws Exception {
        List<TaskItemDTO> taskItemList = new ArrayList<>();
        taskItemList.add(this.taskItemDTO);

        String content = this.mock
                .perform(request(HttpMethod.GET, "/taskItem/readAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(taskItemList), content);
    }

    @Test
    void testUpdate() throws Exception {
        TaskItemDTO newTaskItem = new TaskItemDTO(null, "Eggs");
        TaskItem updatedTaskItem = new TaskItem(newTaskItem.getName());
        updatedTaskItem.setTaskItemId(this.taskItemId);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/taskItem/update/" + this.taskItemId).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newTaskItem)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedTaskItem)), result);
    }

    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/taskItem/delete/" + this.taskItemId)).andExpect(status().isNoContent());
    }


}
