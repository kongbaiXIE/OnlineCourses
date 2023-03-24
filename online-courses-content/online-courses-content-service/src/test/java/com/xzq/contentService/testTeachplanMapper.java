package com.xzq.contentService;

import com.xzq.content.model.dto.TeachplanDto;
import com.xzq.contentService.mapper.TeachplanMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testTeachplanMapper {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    void testTeachplan(){
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDtos);
    }
}
