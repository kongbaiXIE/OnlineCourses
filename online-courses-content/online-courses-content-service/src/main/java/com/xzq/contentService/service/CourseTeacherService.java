package com.xzq.contentService.service;

import com.xzq.content.model.entity.CourseTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service
* @createDate 2023-03-14 23:12:12
*/
public interface CourseTeacherService extends IService<CourseTeacher> {

    List<CourseTeacher> getCourseTeacherInfo(Long courseId);

    CourseTeacher saveCourseTeacher(CourseTeacher teacher);

    void deleteCourseTeacher(Long courseId, Long id);
}
