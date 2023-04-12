package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CheckItem")
public class CheckItemController {

    @Reference//dubbo远程调用
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        try {
            checkItemService.addCheckItem(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping("/delete")
    public Result deleteCheckItem(@RequestParam("id") String id){
        System.out.println("ID==================="+id);
        try {
        checkItemService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/selectById")
    public Result selectCheckItemById(@RequestParam("id")String id){
        CheckItem checkItem = null;
        try {
         checkItem = checkItemService.selectCheckItemById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    @RequestMapping("/EditById")
    public Result editById(@RequestBody CheckItem checkItem){
        try {
        checkItemService.editById(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPageAll")
    public Result findPageAll(){
        List<CheckItem> checkItems = checkItemService.queryAll();
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
    }
}
