package com.itheima.dao;


import com.github.pagehelper.Page;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CheckItemDao {
    public void addCheckItem(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    void deleteById(String id);

    CheckItem selectCheckItemById(String id);

    void editById(CheckItem checkItem);
}
