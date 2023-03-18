package com.xzq.contentService;

import com.xzq.content.model.dto.CourseCategoryTreeDto;
import com.xzq.contentService.mapper.CourseCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContentServiceApplicationTests {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testCourseCategoryMapper(){
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes();
        System.out.println(courseCategoryTreeDtos);
    }
}
