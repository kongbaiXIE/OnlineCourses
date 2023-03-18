package com.xzq.content.model.dto;
import lombok.Data;
import lombok.ToString;

/**
 * 课程查询参数Dto：数据传输对象（DTO）(Data Transfer Object)，用于接口层和业务层之间传输数据
 */
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
