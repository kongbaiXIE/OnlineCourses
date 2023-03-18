package com.xzq.systemService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.system.model.entity.Dictionary;


import java.util.List;

/**
* @author 谢志强
* @description 针对表【dictionary(数据字典)】的数据库操作Service
* @createDate 2023-03-18 11:52:26
*/
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 查询出所有字典的值
     * @return
     */
    List<Dictionary> queryAll();

    /**
     * 根据code查询数据字典
     * @param code -- String 数据字典Code
     * @return
     */
    Dictionary getByCode(String code);

}
