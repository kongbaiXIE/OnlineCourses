package com.xzq.contentService.service;

import com.xzq.content.model.dto.SaveTeachplanDto;
import com.xzq.content.model.dto.TeachplanDto;
import com.xzq.content.model.entity.Teachplan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【teachplan(课程计划)】的数据库操作Service
* @createDate 2023-03-14 23:12:12
*/
public interface TeachplanService extends IService<Teachplan> {
    /**
     * 添加课程计划
     * @param saveTeachplanDto
     */
    void saveTeachPlan(SaveTeachplanDto saveTeachplanDto);


    List<TeachplanDto> deleteTeachplan(Long id);

    void movedown(Long id);

    void moveup(Long id);

    List<TeachplanDto> findTeachplayTree(Long courseId);
}
