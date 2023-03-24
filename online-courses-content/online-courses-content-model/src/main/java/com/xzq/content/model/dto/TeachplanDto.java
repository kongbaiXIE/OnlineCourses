package com.xzq.content.model.dto;

import com.xzq.content.model.entity.Teachplan;
import com.xzq.content.model.entity.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 课程计划树型结构dto
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {
    //课程计划关联的媒资信息
    private TeachplanMedia teachplanMedia;
    //子节点
    private List<TeachplanDto> teachPlanTreeNodes;
}
