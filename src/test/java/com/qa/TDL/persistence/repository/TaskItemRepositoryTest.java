package com.qa.TDL.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.qa.TDL.persistence.domain.TaskItem;


@DataJpaTest
public class TaskItemRepositoryTest {
	
	  @Autowired
	    private TaskItemRepository repo;

	    private final String TEST_NAME = "Owen";
	    private final String TEST_TASK = "Shopping";

	    private final TaskItem TEST_TASKITEM = new TaskItem(TEST_NAME,TEST_TASK);

	    private List<TaskItem> results;

	    @BeforeEach
	    void init() {
	        this.repo.deleteAll();
	        this.results = new ArrayList<>();
	    }

	    @Test
	    void testFindByNameJPQL() throws Exception {
	        this.results.add(TEST_TASKITEM);
	        assertThat(this.repo.findByNameJPQL(TEST_TASKITEM.getName())).isEqualTo(results);
	    }

	    @Test
	    public void testFindByTypeJPQL() throws Exception {
	        this.results.add(TEST_TASKITEM);
	        assertThat(this.repo.findByTypeJPQL(TEST_TASKITEM.getTask())).isEqualTo(results);
	    }

}
