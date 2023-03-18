package com.xzq.base.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页参数
 */
@Data
public class PageRequest implements Serializable {


    private static final long serialVersionUID = -4718633037203279321L;
    /**
     * 页面总数
     */
    protected int pageSize;
    /**
     * 当前页数
     */
    protected int pageNum;
}
