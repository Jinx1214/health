package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupAndCheckItemService;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/CheckGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @Reference
    private CheckGroupAndCheckItemService checkGroupAndCheckItemService;

    @RequestMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroup checkGroup, @RequestParam("checkitemIds")int[] checkitemIds){
        //先插入检查组,再将检查组的id 和 检查项的id插入到中间表中形成关联
        try {
        int checkGroupId = checkGroupService.addCheckGroup(checkGroup);
            System.out.println("插入成功后返回的id为+++++==="+checkGroupId);
        checkGroupAndCheckItemService.addCheckGroupAndCheckItem(checkGroupId,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        //先插入检查组,再将检查组的id 和 检查项的id插入到中间表中形成关联
        PageResult result =  checkGroupService.findPage(queryPageBean);
        return result;
    }
    @RequestMapping("/queryCheckGroupById")
    public Result queryCheckGroupById(@RequestParam("id")Integer id){
        CheckGroup checkGroup = checkGroupService.queryCheckGroupById(id);
        //通过groupId去获取检查项id 通过检查项id 获取对应的检查项
        List<CheckItem> checkItems = checkGroupAndCheckItemService.queryCheckItemByGroupId(id);
        checkGroup.setCheckItems(checkItems);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    @RequestMapping("/update")
    public Result update(@RequestParam("checkitemIds")int[] checkitemIds,@RequestBody CheckGroup checkGroup){
        checkGroupService.updateCheckGroup(checkGroup);
        checkGroupAndCheckItemService.updateGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/deleteGroup")
    public  Result deleteGroup(@RequestParam("id")Integer id){
        checkGroupService.deleteGroup(id);
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> data =  checkGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,data);
    }
}
