package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealAndGroupService;
import com.itheima.service.SetMealService;
import com.itheima.utilities.QiNiuYunConfig;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/SetMeal")
public class SetMealController {

    @Reference
    private SetMealService setMealService;

    @Reference
    private SetMealAndGroupService setMealAndGroupService;

    @RequestMapping("/upload")
    public Result uploadPicture(@RequestParam("imgFile")MultipartFile multipartFile){

        String uploadPictureName = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            uploadPictureName = QiNiuYunConfig.uploadPicture(multipartFile.getName(), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(true, MessageConstant.UPLOAD_SUCCESS,uploadPictureName);
    }

    @RequestMapping("/add")
    public Result addMeal(@RequestParam("checkgroupIds")int[] ids, @RequestBody Setmeal setmeal){
        int setMealId = setMealService.addSetMeal(setmeal);
        setMealAndGroupService.addSetMealAndGroup(ids,setMealId);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        Page<Setmeal> setmealPage =  setMealService.findPage(queryPageBean);
        return new PageResult(setmealPage.getTotal(),setmealPage.getResult());

    }

    @RequestMapping("/Delete")
    public Result DeleteSetMeal(@RequestParam("id")int id){
       setMealService.deleteSetMeal(id);
        return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    @RequestMapping("/Update")
    public Result UpdateSetMeal(@RequestParam("checkgroupIds")int[] ids,@RequestBody Setmeal setmeal){
        setMealService.updateSetMeal(setmeal);
        setMealAndGroupService.updateSetMealAndGroup(ids,setmeal.getId());
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(@RequestParam("id")int id){
        Setmeal  setmeal =  setMealService.findById(id);
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS,setmeal);
    }
}
