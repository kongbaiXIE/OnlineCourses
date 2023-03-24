package com.xzq.contentService.mapper;

import com.xzq.content.model.dto.TeachplanDto;
import com.xzq.content.model.entity.Teachplan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谢志强
* @description 针对表【teachplan(课程计划)】的数据库操作Mapper
* @createDate 2023-03-14 23:12:12
* @Entity generator.domain.Teachplan
*/
@Mapper
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * 查询某课程的课程计划，组成树型结构
     * @param courseId
     * @return
     */
    public List<TeachplanDto> selectTreeNodes(Long courseId);
}




