package com.xzq.contentService.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.content.model.dto.CourseCategoryTreeDto;
import com.xzq.content.model.entity.CourseCategory;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【course_category(课程分类)】的数据库操作Service
* @createDate 2023-03-14 23:12:12
*/
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 课程分类树形结构查询
     */
    public List<CourseCategoryTreeDto> queryTreeNodes();

}
