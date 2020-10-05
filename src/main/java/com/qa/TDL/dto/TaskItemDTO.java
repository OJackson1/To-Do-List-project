package com.qa.TDL.dto;

import com.qa.TDL.dto.TaskItemDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskItemDTO {
	

    private Long taskItemid;
    private String name;
    private String tasks;


}
