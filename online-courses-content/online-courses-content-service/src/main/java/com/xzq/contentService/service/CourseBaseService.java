package com.xzq.contentService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.base.common.PageRequest;
import com.xzq.content.model.dto.AddCourseDto;
import com.xzq.content.model.dto.CourseBaseInfoDto;
import com.xzq.content.model.dto.EditCourseDto;
import com.xzq.content.model.dto.QueryCourseParamsDto;
import com.xzq.content.model.entity.CourseBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 谢志强
* @description 针对表【course_base(课程基本信息)】的数据库操作Service
* @createDate 2023-03-14 23:12:12
*/
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * 课程分页查询接口
     * @param pageRequest 分页参数
     * @param queryCourseParamsDto 条件
     * @return
     */
    Page<CourseBase> queryCourseBaseList(PageRequest pageRequest, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 添加课程基本信息
     * @param companyId 教学机构id
     * @param addCourseDto 课程基本信息
     * @return
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * 根据课程id查询课程基本信息，包括基本信息和营销信息
     * @param courseId
     * @return
     */
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    /**
     * 修改课程信息
     * @param companyId
     * @param editCourseDto
     * @return
     */
    CourseBaseInfoDto updateCourseBaseInfo(Long companyId, EditCourseDto editCourseDto);

    void deleteCourseBase(Long courseId);
}
