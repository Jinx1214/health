package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

public interface SetMealDao {
    int  addSetMeal(Setmeal setmeal);

    Page<Setmeal> findPage(@Param("queryString") String queryString);

    void deleteSetMeal(int id);

    void updateSetMeal(Setmeal setmeal);

    Setmeal findById(int id);
}
