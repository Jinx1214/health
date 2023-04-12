package com.itheima.service;


import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService  {

    void addCheckItem(CheckItem checkItem);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(String id);

    CheckItem selectCheckItemById(String id);

    void editById(CheckItem checkItem);

    List<CheckItem> queryAll();
}
