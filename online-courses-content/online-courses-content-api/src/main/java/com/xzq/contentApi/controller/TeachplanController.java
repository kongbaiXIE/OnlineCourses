package com.xzq.contentApi.controller;

import com.xzq.content.model.dto.SaveTeachplanDto;
import com.xzq.content.model.dto.TeachplanDto;
import com.xzq.contentService.service.TeachplanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TeachplanController {

    @Resource
    TeachplanService teachplanService;

    /**
     * @description 查询课程计划
     * @param courseId 课程id
     * @return
     */
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplayTree(courseId);
    }
    /**
     * 添加或修改课程计划
     * @param saveTeachplanDto
     */
    @PostMapping("/teachplan")
    public void saveTeachPlan(@RequestBody  SaveTeachplanDto saveTeachplanDto){
        teachplanService.saveTeachPlan(saveTeachplanDto);
    }
    /**
     * 删除课程计划
     * @param id
     * @return
     */
    @DeleteMapping("/teachplan/{id}")
    public List<TeachplanDto> deleteTeachplan(@PathVariable Long id){
        return teachplanService.deleteTeachplan(id);
    }

    /**
     * 向下移动
     */
    @PostMapping("/teachplan/movedown/{id}")
    public void movedown(@PathVariable Long id){
        teachplanService.movedown(id);
    }
    /**
     * 向上移动
     */
    @PostMapping("/teachplan/moveup/{id}")
    public void moveup(@PathVariable Long id){
        teachplanService.moveup(id);
    }


}
