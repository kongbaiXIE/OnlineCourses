package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xzq.base.common.ErrorCode;
import com.xzq.base.common.PageRequest;
import com.xzq.base.exeception.BusinessException;
import com.xzq.content.model.dto.AddCourseDto;
import com.xzq.content.model.dto.CourseBaseInfoDto;
import com.xzq.content.model.dto.EditCourseDto;
import com.xzq.content.model.dto.QueryCourseParamsDto;
import com.xzq.content.model.entity.*;
import com.xzq.contentService.mapper.*;
import com.xzq.contentService.service.CourseBaseService;
import com.xzq.contentService.service.CourseMarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;


/**
* @author 谢志强
* @description 针对表【course_base(课程基本信息)】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
    implements CourseBaseService {

    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseMarketMapper courseMarketMapper;
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Autowired
    CourseMarketService courseMarketService;

    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Autowired
    TeachplanMapper teachplanMapper;


    /**
     * 课程分页条件查询
     * @param pageRequest
     * @param queryCourseParamsDto
     * @return
     */
    @Override
    public Page<CourseBase> queryCourseBaseList(PageRequest pageRequest, QueryCourseParamsDto queryCourseParamsDto) {
        QueryWrapper<CourseBase> courseBaseQueryWrapper = new QueryWrapper<>();
        String courseName = queryCourseParamsDto.getCourseName();
        String auditStatus = queryCourseParamsDto.getAuditStatus();
        if (StringUtils.isNotEmpty(courseName)){
            courseBaseQueryWrapper.like("name",courseName);
        }
        if (StringUtils.isNotEmpty(auditStatus)){
            courseBaseQueryWrapper.eq("audit_status",auditStatus);
        }
        Page<CourseBase> basePage = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());

        return courseBaseMapper.selectPage(basePage, courseBaseQueryWrapper);
    }

    /**
     * 添加课程基本信息
     * @param companyId 教学机构id
     * @param addCourseDto 课程基本信息
     * @return
     */
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        //检测参数是否合法
        if (StringUtils.isBlank(addCourseDto.getName())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"课程名称为空");
        }
        if (StringUtils.isBlank(addCourseDto.getMt())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"课程分类为空");
        }
        if (StringUtils.isBlank(addCourseDto.getSt())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"课程分类为空");
        }
        if (StringUtils.isBlank(addCourseDto.getGrade())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"课程等级为空");
        }
        if (StringUtils.isBlank(addCourseDto.getTeachmode())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"教育模式为空");
        }
        if (StringUtils.isBlank(addCourseDto.getUsers())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"适用人群为空");
        }
        if (StringUtils.isBlank(addCourseDto.getCharge())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"收费规则为空");
        }
        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将传递过来的新增课程数据直接通过拷贝赋值
        BeanUtils.copyProperties(addCourseDto,courseBaseNew);
        //添加机构id
        courseBaseNew.setCompanyId(companyId);
        //添加时间
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //插入课程基本信息
        int insertBase = courseBaseMapper.insert(courseBaseNew);
        Long courseId = courseBaseNew.getId();

        //课程营销信息
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto,courseMarketNew);
        courseMarketNew.setId(courseId);
        //设置收费规则
        String charge = addCourseDto.getCharge();
        //收费课程必须写价格且价格大于0
        if (charge.equals("201001")){
            Float price = addCourseDto.getPrice();
            if (price==null || price.floatValue() <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"收费课程必须写价格且价格大于0");
            }
        }
        BeanUtils.copyProperties(addCourseDto,courseMarketNew);
        //插入课程营销信息
        int insertMarket = courseMarketMapper.insert(courseMarketNew);
        if (insertBase<=0 || insertMarket<=0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"课程相关数据添加失败");
        }
        return getCourseBaseInfo(courseId);
    }
    /**
     * 根据课程id查询课程基本信息，包括基本信息和营销信息
     * @param courseId
     * @return
     */
    @Override
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId){
        //获取课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        //获取课程营销数据
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if(courseBase == null){
            return null;
        }
        //将数据库中的数据通过拷贝，赋值到封装的对象
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if(courseMarket != null){
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        }
        //查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());
        return courseBaseInfoDto;
    }

    @Override
    public CourseBaseInfoDto updateCourseBaseInfo(Long companyId, EditCourseDto editCourseDto) {
        CourseBase courseBaseUpdate = courseBaseMapper.selectById(editCourseDto.getId());
        if (courseBaseUpdate == null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"没有查到该课程");
        }
        //只能修改自己机构的课
        if (!companyId.equals(courseBaseUpdate.getCompanyId())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"只允许修改自己机构的课程");
        }
        //将要修改的信息拷贝
        BeanUtils.copyProperties(editCourseDto,courseBaseUpdate);
        courseBaseUpdate.setChangeDate(LocalDateTime.now());
        int updateById = courseBaseMapper.updateById(courseBaseUpdate);
        if (updateById < 0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(editCourseDto,courseMarket);
        saveMarketCourse(courseMarket);
        
        //查询修改后的课程信息
        return getCourseBaseInfo(editCourseDto.getId());
    }

    @Transactional
    @Override
    public void deleteCourseBase(Long courseId) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseBase::getId, courseId);
        CourseBase courseBase = courseBaseMapper.selectOne(queryWrapper);
        String status = courseBase.getStatus();
        if (!status.equals("203001")) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"课程未处于未提交状态，无法删除");
        }
        //courseteacher 找courseid再删
        LambdaQueryWrapper<CourseTeacher> query3 = new LambdaQueryWrapper<>();
        query3.eq(CourseTeacher::getCourseId, courseId);
        courseTeacherMapper.delete(query3);

        //teachplanmedia 找courseid再删
        LambdaQueryWrapper<TeachplanMedia> query2 = new LambdaQueryWrapper<>();
        query2.eq(TeachplanMedia::getCourseId, courseId);
        teachplanMediaMapper.delete(query2);

        //teachplan 找courseid再删
        LambdaQueryWrapper<Teachplan> query1 = new LambdaQueryWrapper<>();
        query1.eq(Teachplan::getCourseId, courseId);
        teachplanMapper.delete(query1);

        //coursebase 删id
        //coursemarket 删id
        Long id = courseBase.getId();
        courseMarketMapper.deleteById(id);
        courseBaseMapper.deleteById(id);
    }

    /**
     * 添加和修改课程营销信息
     * @param courseMarket
     * @return
     */
    public int saveMarketCourse(CourseMarket courseMarket){
        String charge = courseMarket.getCharge();
        //收费课程必须写价格且价格大于0
        if (charge.equals("201001")){
            Float price = courseMarket.getPrice();
            Float originalPrice = courseMarket.getOriginalPrice();
            if(originalPrice == null || originalPrice <= 0 || price == null || price.floatValue() <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"收费课程必须写价格且价格大于0");
            }
        }
        //免费课程
        if (charge.equals("201000")){
            Float price = courseMarket.getPrice();
            Float originalPrice = courseMarket.getOriginalPrice();
            if(originalPrice != null || originalPrice > 0 || price != null || price.floatValue() > 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"免费课程,请勿添加金额");
            }
            courseMarket.setPrice(0f);
            courseMarket.setOriginalPrice(0f);
        }
        boolean b = courseMarketService.saveOrUpdate(courseMarket);

        return b? 1:-1;

    }
}




