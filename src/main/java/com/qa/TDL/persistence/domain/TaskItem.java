package com.qa.TDL.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskItem {
	
	@Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskItemId;

    @NotNull
    @Size(min = 0, max = 55)
    private String name;

    @NotNull
    private String tasks;

    @ManyToOne
    private Task task;

    public TaskItem(String name, String tasks) {
        super();
        this.name = name;
        this.tasks = tasks;
    }


}
