package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

public interface CheckGroupService {
    int addCheckGroup(CheckGroup checkGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup queryCheckGroupById(Integer id);

    void updateCheckGroup(CheckGroup checkGroup);

    void deleteGroup(Integer id);
}
