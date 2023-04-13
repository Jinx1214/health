package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //添加检查组
    @Override
    public int addCheckGroup(CheckGroup checkGroup) {
        checkGroupDao.addCheckGroup(checkGroup);
        System.out.println("返回的checkGroupId=="+checkGroup.getId());
        return checkGroup.getId();
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //使用pageHelper进行查询
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> checkGroups =  checkGroupDao.queryList(queryPageBean.getQueryString());
        return new PageResult(checkGroups.getTotal(),checkGroups.getResult());
    }

    @Override
    public CheckGroup queryCheckGroupById(Integer id) {
        CheckGroup group = checkGroupDao.queryCheckGroup(id);
        return group;
    }

    @Override
    public void updateCheckGroup(CheckGroup checkGroup) {
        //更新checkGroup信息
        checkGroupDao.updateCheckGroup(checkGroup);
        //更新Group和Item关联表的信息  先删除所有关联的信息 然后再插入新的检查项

    }

    @Override
    public void deleteGroup(Integer id) {
        checkGroupDao.deleteGroup(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        List<CheckGroup> data = checkGroupDao.findAll();
        return data;
    }
}
