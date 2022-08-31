package com.chy.model;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果模型，存放整个结果分页基本信息和当前页信息以及内容
 */
@Setter
@Getter
@NoArgsConstructor
public class Paging<T> implements Serializable {

    /**
     * 当前页
     */
    private int currentPageNum;

    /**
     * 总页数
     */
    private int totalPageNum;

    /**
     * 每页记录数量
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 当前页记录集合
     */
    List<T> dataCurrentPage;

    /**
     * 传入Page结果对象构造Pageing对象
     *
     * @param pageResult 分页查询结果Page对象
     */
    public Paging(Page<T> pageResult) {
        this.currentPageNum = pageResult.getPageNum();
        this.totalPageNum = pageResult.getPages();
        this.totalCount = pageResult.getTotal();
        this.pageSize = pageResult.getPageSize();
        this.dataCurrentPage = pageResult.getResult();
    }

}

