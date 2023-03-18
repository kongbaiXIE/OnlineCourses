package com.xzq.contentService.mapper;

import com.xzq.content.model.dto.CourseCategoryTreeDto;
import com.xzq.content.model.entity.CourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【course_category(课程分类)】的数据库操作Mapper
* @createDate 2023-03-14 23:12:12
* @Entity generator.domain.CourseCategory
*/
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {


    /**
     * 生成树形结构的课程分类对象
     * @return
     */
    List<CourseCategoryTreeDto> selectTreeNodes();
}




