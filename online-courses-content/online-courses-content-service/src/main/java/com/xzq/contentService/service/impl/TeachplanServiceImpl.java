package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.base.common.ErrorCode;
import com.xzq.base.exeception.BusinessException;
import com.xzq.content.model.dto.SaveTeachplanDto;
import com.xzq.content.model.dto.TeachplanDto;
import com.xzq.content.model.entity.Teachplan;
import com.xzq.content.model.entity.TeachplanMedia;
import com.xzq.contentService.mapper.TeachplanMapper;
import com.xzq.contentService.mapper.TeachplanMediaMapper;
import com.xzq.contentService.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【teachplan(课程计划)】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
    implements TeachplanService {


    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    /**
     * 添加课程计划
     * @param saveTeachplanDto
     */
    @Override
    public void saveTeachPlan(SaveTeachplanDto saveTeachplanDto) {
        Long id = saveTeachplanDto.getId();
        if (id != null){
            //如果课程计划id存在则表明课程计划是修改
            Teachplan teachplanNew = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(saveTeachplanDto,teachplanNew);
            teachplanMapper.updateById(teachplanNew);
            //相反则是添加
        }else {
            Teachplan teachplan = new Teachplan();
            Long courseId = saveTeachplanDto.getCourseId();
            Long parentid = saveTeachplanDto.getParentid();
            int countByOrder = getTeachplanCount(courseId, parentid);
            //设置排序号
            teachplan.setOrderby(countByOrder+1);
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            teachplanMapper.insert(teachplan);
        }
    }
    @Transactional
    @Override
    public List<TeachplanDto> deleteTeachplan(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        if (teachplan == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        Long courseId = teachplan.getCourseId();
        QueryWrapper<Teachplan> teachplanQueryWrapper= new QueryWrapper<>();
        //如果存在子章节则不能直接删除父章节
        teachplanQueryWrapper.eq("parentid",id);
        Integer count = teachplanMapper.selectCount(teachplanQueryWrapper);
        if (count>0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"该章节存在子章节");
        }
        //删除子章节连同器视频资源一起删除
        Long teachplanId = teachplan.getId();
        QueryWrapper<TeachplanMedia> teachplanMediaQueryWrapper = new QueryWrapper<>();
        teachplanMediaQueryWrapper.eq("teachplan_id",teachplanId);
        Integer count1 = teachplanMediaMapper.selectCount(teachplanMediaQueryWrapper);
        if (count1>0){
            teachplanMediaMapper.delete(teachplanMediaQueryWrapper);
        }
        //子章节删除完后再删除父章节
        teachplanMapper.deleteById(teachplanId);

        return teachplanMapper.selectTreeNodes(courseId);
    }

    /**
     * 下移
     * @param id
     */
    @Override
    public void movedown(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        Integer targetOrderby = teachplan.getOrderby();

        QueryWrapper<Teachplan> teachplanQueryWrapper = new QueryWrapper<>();
        teachplanQueryWrapper.eq("parentid",parentid);
        //将大于这些该章节的需要统计出来
        teachplanQueryWrapper.gt("orderby",targetOrderby);
        Integer count = teachplanMapper.selectCount(teachplanQueryWrapper);
        if (count == 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "已经是最后一个，无法下移");
        }else {
            //查询出该章节的下一章节
            teachplanQueryWrapper.eq("orderby",targetOrderby+1);
            Teachplan preTeachplan = teachplanMapper.selectOne(teachplanQueryWrapper);
            //将下一章节的序号减一
            preTeachplan.setOrderby(preTeachplan.getOrderby()-1);
            teachplanMapper.updateById(preTeachplan);
            teachplan.setOrderby(targetOrderby +1);
        }
        teachplanMapper.updateById(teachplan);
    }

    /**
     * 上移
     * @param id
     */
    @Override
    public void moveup(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        Integer targetOrderby = teachplan.getOrderby();
        if (targetOrderby == 1){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"已经是第一个，无法上移");
        }
        QueryWrapper<Teachplan> teachplanQueryWrapper = new QueryWrapper<>();
        teachplanQueryWrapper.eq("parentid",parentid);
        //将小于这些该章节的需要统计出来
        teachplanQueryWrapper.lt("orderby",targetOrderby);
        Integer count = teachplanMapper.selectCount(teachplanQueryWrapper);
        if (count > 1){
            //查询出该章节的上一章节 上一节排序小于后面章节排序
            teachplanQueryWrapper.eq("orderby",targetOrderby-1);
            Teachplan preTeachplan = teachplanMapper.selectOne(teachplanQueryWrapper);
            //将下一章节的序号加一 表示向后移动
            preTeachplan.setOrderby(preTeachplan.getOrderby()+1);
            teachplanMapper.updateById(preTeachplan);
            //同时将需要上移的章节 排序号减一
            teachplan.setOrderby(targetOrderby -1);
        }
        teachplanMapper.updateById(teachplan);
    }

    /**
     * 查询课程计划
     * @param courseId
     * @return
     */
    @Override
    public List<TeachplanDto> findTeachplayTree(Long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    /**
     * 获取最新的排序号
     * @param courseId
     * @param parentid
     * @return
     */
    public int getTeachplanCount(Long courseId,Long parentid){
        QueryWrapper<Teachplan> teachplanQueryWrapper = new QueryWrapper<>();
        teachplanQueryWrapper.eq("course_id",courseId);
        teachplanQueryWrapper.eq("parentid",parentid);
        Integer count = teachplanMapper.selectCount(teachplanQueryWrapper);
        return count;

    }
}




