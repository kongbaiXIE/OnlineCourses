package com.xzq.base.exeception;

//定义到实体类上的分组校验规则（就是针对同一个实体类，在不同的情况下需要进行校验的规则进行分类设置）

public class ValidationGroups {
    /**
     * 新增情况下的校验规则
     */
    public interface Inster {

    }

    /**
     * 更新情况下的校验规则
     */
    public interface Update {

    }

    /**
     * 删除情况下的校验规则
     */
    public interface Delete {

    }


}
