package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetMealAndGroupDao;
import com.itheima.dao.SetMealDao;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealDao setMealDao;

    @Autowired
    private SetMealAndGroupDao setMealAndGroupDao;

    @Override
    public int addSetMeal(Setmeal setmeal) {

       setMealDao.addSetMeal(setmeal);
        System.out.println("新增套餐Id为:===="+setmeal.getId());
       return setmeal.getId();
    }

    @Override
    public Page<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> pages =  setMealDao.findPage(queryPageBean.getQueryString());
        return pages;
    }

    @Override
    public void deleteSetMeal(int id) {
        setMealDao.deleteSetMeal(id);
    }

    @Override
    public void updateSetMeal(Setmeal setmeal) {
        setMealDao.updateSetMeal(setmeal);
    }

    @Override
    public Setmeal findById(int id) {
        Setmeal Setmeal = setMealDao.findById(id);
        List<CheckGroup> group = setMealAndGroupDao.getGroup(id);
        Setmeal.setCheckGroups(group);
        return Setmeal;
    }
}
