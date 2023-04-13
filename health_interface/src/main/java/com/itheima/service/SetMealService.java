package com.itheima.service;

import com.github.pagehelper.Page;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

public interface SetMealService {
    int addSetMeal(Setmeal setmeal);

    Page<Setmeal> findPage(QueryPageBean queryPageBean);

    void deleteSetMeal(int id);

    void updateSetMeal(Setmeal setmeal);

    Setmeal findById(int id);
}
