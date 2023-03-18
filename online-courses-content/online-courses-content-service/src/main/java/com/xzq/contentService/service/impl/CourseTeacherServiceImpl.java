package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xzq.content.model.entity.CourseTeacher;
import com.xzq.contentService.mapper.CourseTeacherMapper;
import com.xzq.contentService.service.CourseTeacherService;
import org.springframework.stereotype.Service;

/**
* @author 谢志强
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher>
    implements CourseTeacherService {

}




