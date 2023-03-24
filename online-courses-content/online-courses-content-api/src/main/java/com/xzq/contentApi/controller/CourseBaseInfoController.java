package com.xzq.contentApi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.base.common.BaseResponse;
import com.xzq.base.common.ErrorCode;
import com.xzq.base.common.PageRequest;
import com.xzq.base.common.ResultUtil;
import com.xzq.base.exeception.BusinessException;
import com.xzq.base.exeception.ValidationGroups;
import com.xzq.content.model.dto.AddCourseDto;
import com.xzq.content.model.dto.CourseBaseInfoDto;
import com.xzq.content.model.dto.EditCourseDto;
import com.xzq.content.model.dto.QueryCourseParamsDto;
import com.xzq.content.model.entity.CourseBase;
import com.xzq.contentService.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 课程信息编辑接口
 */
@Api(value = "课程信息管理接口",tags = "课程信息管理接口")
@RestController
public class CourseBaseInfoController {

    @Resource
    CourseBaseService courseBaseService;

    /**
     * 课程分页查询接口
     * @param pageRequest
     * @param queryCourseParamsDto
     * @return
     */
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public Page<CourseBase> list(PageRequest pageRequest, @RequestBody(required=false) QueryCourseParamsDto queryCourseParamsDto) {

        Page<CourseBase> courseBasePage = courseBaseService.queryCourseBaseList(pageRequest, queryCourseParamsDto);
        if (StringUtils.isEmpty(courseBasePage)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //return ResultUtil.success(courseBasePage);

        return courseBasePage;
    }
    /**
     * 添加课程基本信息
     * @param addCourseDto
     * @return
     */
    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto addCourseBase(@RequestBody @Validated({ValidationGroups.Inster.class}) AddCourseDto addCourseDto){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        //if (companyId < 0){
        //    throw new BusinessException(ErrorCode.PARAMS_ERROR);
        //}
        CourseBaseInfoDto courseBase = courseBaseService.createCourseBase(companyId, addCourseDto);
        if (StringUtils.isEmpty(courseBase)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //return ResultUtil.success(courseBase);
        return courseBase;
    }

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseInfo(@PathVariable Long courseId){
        return courseBaseService.getCourseBaseInfo(courseId);
    }

    @PutMapping("/course")
    public CourseBaseInfoDto updateCourseBaseInfo(@RequestBody @Validated({ValidationGroups.Update.class}) EditCourseDto editCourseDto){
        Long companyId = 1232141425L;
        return courseBaseService.updateCourseBaseInfo(companyId, editCourseDto);
    }

    /**
     * 删除课程
     * @param courseId
     */
    @DeleteMapping("/course/{courseId}")
    public void deleteCourseBase(@PathVariable @Validated Long courseId){
        courseBaseService.deleteCourseBase(courseId);
    }


}
