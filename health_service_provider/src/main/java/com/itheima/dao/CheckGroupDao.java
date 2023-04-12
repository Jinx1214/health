package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

public interface CheckGroupDao {
    int addCheckGroup(CheckGroup checkGroup);

    Page<CheckGroup> queryList(String queryString);

    CheckGroup queryCheckGroup(Integer id);

    void updateCheckGroup(CheckGroup checkGroup);

    void deleteGroup(Integer id);
}
