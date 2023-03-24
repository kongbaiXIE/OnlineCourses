package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xzq.content.model.entity.CourseTeacher;
import com.xzq.contentService.mapper.CourseTeacherMapper;
import com.xzq.contentService.service.CourseTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 谢志强
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher>
    implements CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> getCourseTeacherInfo(Long courseId) {
        //通过课程id查询到相关的教师信息
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);
        CourseTeacher courseTeacher = courseTeacherMapper.selectOne(lambdaQueryWrapper);
        ArrayList<CourseTeacher> courseTeachers = new ArrayList<>();
        if (courseTeacher == null){
            courseTeachers.add(new CourseTeacher());
        }
        courseTeachers.add(courseTeacher);

        return courseTeachers;
    }
    @Transactional
    @Override
    public CourseTeacher saveCourseTeacher(CourseTeacher teacher) {
        Long courseId = teacher.getCourseId();
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);
        //查询是否存在该课程，如果不存在着通过拷贝的方式添加
        Integer count = courseTeacherMapper.selectCount(lambdaQueryWrapper);
        if (count < 1){
            CourseTeacher courseTeacher = new CourseTeacher();
            BeanUtils.copyProperties(teacher,courseTeacher);
            courseTeacherMapper.insert(courseTeacher);
            return getCourseTeacher(courseTeacher);
        }
        //如果大于1则表示进行修改
        CourseTeacher courseTeacher = courseTeacherMapper.selectOne(lambdaQueryWrapper);
        Long id = courseTeacher.getId();
        BeanUtils.copyProperties(teacher,courseTeacher);
        courseTeacher.setId(id);
        courseTeacherMapper.updateById(courseTeacher);
        //更新之后通过课程id，由于课程id不变，通过课程id查出更新后的教师课程关系表信息
        Long courseId1 = courseTeacher.getCourseId();
        LambdaQueryWrapper<CourseTeacher> courseTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseTeacherLambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId1);

        return courseTeacherMapper.selectOne(courseTeacherLambdaQueryWrapper);
    }
    @Transactional
    @Override
    public void deleteCourseTeacher(Long courseId, Long id) {

        courseTeacherMapper.deleteById(id);


    }
    private CourseTeacher getCourseTeacher(CourseTeacher courseTeacher) {
        Long courseId = courseTeacher.getCourseId();
        LambdaQueryWrapper<CourseTeacher> query = new LambdaQueryWrapper<>();
        query.eq(CourseTeacher::getCourseId, courseId);
        return courseTeacherMapper.selectOne(query);
    }
}




