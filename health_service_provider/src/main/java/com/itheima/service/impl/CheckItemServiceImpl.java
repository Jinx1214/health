package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void addCheckItem(CheckItem checkItem) {
        checkItemDao.addCheckItem(checkItem);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(String id) {
      checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem selectCheckItemById(String id) {

        return checkItemDao.selectCheckItemById(id);
    }

    @Override
    public void editById(CheckItem checkItem) {
        checkItemDao.editById(checkItem);
    }
}
