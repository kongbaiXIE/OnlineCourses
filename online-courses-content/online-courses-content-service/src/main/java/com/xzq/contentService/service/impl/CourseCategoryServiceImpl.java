package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.content.model.dto.CourseCategoryTreeDto;
import com.xzq.content.model.entity.CourseCategory;
import com.xzq.contentService.mapper.CourseCategoryMapper;
import com.xzq.contentService.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【course_category(课程分类)】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
    implements CourseCategoryService {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryMapper.selectTreeNodes();
    }
}




