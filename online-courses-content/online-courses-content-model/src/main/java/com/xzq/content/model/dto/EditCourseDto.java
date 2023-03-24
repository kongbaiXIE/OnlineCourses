package com.xzq.content.model.dto;

import lombok.Data;

/**
 * 根据id修改课程信息
 */
@Data
public class EditCourseDto extends AddCourseDto {

    //课程id
    private Long id;
}
