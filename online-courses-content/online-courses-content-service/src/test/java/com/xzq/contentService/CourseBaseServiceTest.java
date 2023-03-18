package com.xzq.contentService;

import com.xzq.content.model.entity.CourseBase;
import com.xzq.contentService.mapper.CourseBaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试mapper的连通性
 */
@SpringBootTest
public class CourseBaseServiceTest {

    @Resource
    CourseBaseMapper courseBaseMapper;

    @Test
    void CourseBaseMapperTest(){
        CourseBase courseBase = courseBaseMapper.selectById(1);
        System.out.println(courseBase);
    }

}
