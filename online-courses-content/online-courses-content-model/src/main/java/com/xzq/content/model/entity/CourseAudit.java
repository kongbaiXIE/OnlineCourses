package com.xzq.content.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName course_audit
 */
@TableName(value ="course_audit")
@Data
public class CourseAudit implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Long courseId;

    /**
     * 审核意见
     */
    @TableField(value = "audit_mind")
    private String auditMind;

    /**
     * 审核状态
     */
    @TableField(value = "audit_status")
    private String auditStatus;

    /**
     * 审核人
     */
    @TableField(value = "audit_people")
    private String auditPeople;

    /**
     * 审核时间
     */
    @TableField(value = "audit_date")
    private Date auditDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}