package com.xzq.systemApi.controller;

import com.xzq.base.common.ErrorCode;
import com.xzq.base.exeception.BusinessException;
import com.xzq.system.model.entity.Dictionary;
import com.xzq.systemService.service.DictionaryService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class DictionaryController {

    @Resource
    DictionaryService dictionaryService;

    @GetMapping("/dictionary/all")
    public List<Dictionary> queryAll(){
        List<Dictionary> dictionaries = dictionaryService.queryAll();
        if (StringUtils.isEmpty(dictionaries)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return dictionaries;
    }
    @GetMapping("/dictionary/code/{code}")
    public Dictionary getDictionaryByCode(@PathVariable String code){
        Dictionary dictionaryByCode = dictionaryService.getByCode(code);
        if (StringUtils.isEmpty(dictionaryByCode)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return dictionaryByCode;
    }

}
