package com.xzq.systemService.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.system.model.entity.Dictionary;
import com.xzq.systemService.mapper.DictionaryMapper;
import com.xzq.systemService.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 谢志强
* @description 针对表【dictionary(数据字典)】的数据库操作Service实现
* @createDate 2023-03-18 11:52:26
*/
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary>
    implements DictionaryService {

    @Resource
    DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> queryAll() {
        List<Dictionary> dictionaries = this.list();
        return dictionaries;
    }

    @Override
    public Dictionary getByCode(String code) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("code",code);
        Dictionary dictionary = dictionaryMapper.selectById(dictionaryQueryWrapper);
        return dictionary;
    }
}




