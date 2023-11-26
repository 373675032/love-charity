package com.charity.vo;

import com.charity.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectVo {
    private Project project;
    private String userName;
    private int comments;
}
