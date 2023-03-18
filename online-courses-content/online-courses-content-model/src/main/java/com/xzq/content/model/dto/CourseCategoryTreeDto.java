package com.xzq.content.model.dto;

import com.xzq.content.model.entity.CourseCategory;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 课程分类树型结点dto
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    List<CourseCategoryTreeDto> childrenTreeNodes;
}
