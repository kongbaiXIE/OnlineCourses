package com.xzq.contentApi.controller;

import com.xzq.content.model.entity.CourseTeacher;
import com.xzq.contentService.service.CourseTeacherService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CourseTeacherController {

    @Resource
    CourseTeacherService courseTeacherService;

    /**
     * @description 查询课程老师
     * @param courseId 课程id
     */
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> selectCourseTeacher(@PathVariable Long courseId){
        return courseTeacherService.getCourseTeacherInfo(courseId);
    }

    /**
     * @description 添加/修改课程老师
     * @param  teacher 教师信息
     */
    @PostMapping("/courseTeacher")
    public CourseTeacher saveCourseTeacher(@RequestBody @Validated CourseTeacher teacher){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseTeacherService.saveCourseTeacher(teacher);
    }


    /**
     * @description 删除课程老师
     * @param courseId 课程id
     * @param id 教师id
     */
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteCourseTeacher(@PathVariable Long courseId, @PathVariable Long id){
        courseTeacherService.deleteCourseTeacher(courseId, id);
    }
}


