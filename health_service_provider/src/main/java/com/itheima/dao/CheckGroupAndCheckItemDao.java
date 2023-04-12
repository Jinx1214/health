package com.itheima.dao;

import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupAndCheckItemDao {
    void addCheckGroupAndCheckItem(@Param("id") Integer checkGroupId, @Param("ids")int[] checkItemIds);

    List<CheckItem> queryCheckItemByGroupId(Integer id);

    //删除所有的关联项
    void deleteGroupAndCheckItem(@Param("id") Integer id);
}
