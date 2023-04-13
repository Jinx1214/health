package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.SetMealAndGroupDao;
import com.itheima.service.SetMealAndGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetMealAndGroupService.class)
@Transactional
public class SetMealAndGroupServiceImpl implements SetMealAndGroupService{

    @Autowired
    private SetMealAndGroupDao setMealAndGroupDao;


    @Override
    public void addSetMealAndGroup(int[] ids, Integer id) {
        setMealAndGroupDao.addSetMealAndGroup(ids,id);
    }

    @Override
    public void updateSetMealAndGroup(int[] ids, Integer id) {
        setMealAndGroupDao.delete(id);
        setMealAndGroupDao.addSetMealAndGroup(ids,id);
    }
}
