package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.CheckGroupAndCheckItemDao;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupAndCheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupAndCheckItemService.class)
@Transactional
public class CheckGroupAndCheckItemServiceImpl implements CheckGroupAndCheckItemService {
    @Autowired
    private CheckGroupAndCheckItemDao checkGroupAndCheckItemDao;
    @Override
    public void addCheckGroupAndCheckItem(Integer checkGroupId, int[] checkItemIds) {
        System.out.println("为什么新增进去为0========="+checkGroupId);
        checkGroupAndCheckItemDao.addCheckGroupAndCheckItem(checkGroupId,checkItemIds);
    }

    @Override
    public List<CheckItem> queryCheckItemByGroupId(Integer id) {
        List<CheckItem> list =  checkGroupAndCheckItemDao.queryCheckItemByGroupId(id);
        return list;
    }

    @Override
    public void updateGroupAndCheckItem(Integer id, int[] ids) {
        //删除所有关联
        checkGroupAndCheckItemDao.deleteGroupAndCheckItem(id);
        //进行新增关联
        addCheckGroupAndCheckItem(id,ids);
    }
}
