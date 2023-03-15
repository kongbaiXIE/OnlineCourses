package com.xzq.content.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName teachplan_work
 */
@TableName(value ="teachplan_work")
@Data
public class TeachplanWork implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作业信息标识
     */
    @TableField(value = "work_id")
    private Long workId;

    /**
     * 作业标题
     */
    @TableField(value = "work_title")
    private String workTitle;

    /**
     * 课程计划标识
     */
    @TableField(value = "teachplan_id")
    private Long teachplanId;

    /**
     * 课程标识
     */
    @TableField(value = "course_id")
    private Long courseId;

    /**
     * 
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 
     */
    @TableField(value = "course_pub_id")
    private Long coursePubId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}