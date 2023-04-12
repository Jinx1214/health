package com.itheima.service;

import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckGroupAndCheckItemService {
    void addCheckGroupAndCheckItem(Integer checkGroupId,int[] checkItemIds);

    List<CheckItem> queryCheckItemByGroupId(Integer id);

    void updateGroupAndCheckItem(Integer id, int[] ids);
}
