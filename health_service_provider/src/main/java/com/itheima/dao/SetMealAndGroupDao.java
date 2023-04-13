package com.itheima.dao;

import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetMealAndGroupDao {
    void addSetMealAndGroup(@Param("ids")int[] ids,@Param("id")Integer id);

    void delete(Integer id);


    List<CheckGroup> getGroup(int id);
}
