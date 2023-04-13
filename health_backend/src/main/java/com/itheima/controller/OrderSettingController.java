package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utilities.POIUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/OrderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;


    @RequestMapping("/upload")
    public Result uploadXlsx(@RequestParam("excelFile")MultipartFile multipartFile) throws IOException {

            POIUtils.checkFile(multipartFile);
            List<String[]> strings = POIUtils.readExcel(multipartFile);
            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] string : strings) {
                String date = string[0];
                String number = string[1];
                OrderSetting orderSetting = new OrderSetting(new Date(date), Integer.parseInt(number));
                orderSettings.add(orderSetting);
            }
            //进行循环插入
            orderSettingService.add(orderSettings);
        return new Result(true, MessageConstant.UPLOAD_SUCCESS);
    }

    @RequestMapping("/Reservations")
    public Result getReservations(@RequestParam("date")String date){
        System.out.println(date);
        List<OrderSetting> orderSettings = orderSettingService.getReservations(date);
        //将查询到的所有预约信息进行遍历封装
        List<Map> resultData = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettings) {
            Map map = new HashMap();
            //date: 1, number: 120, reservations: 1
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            resultData.add(map);
        }
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,resultData);
    }

    @RequestMapping("/setReservations")
    public Result setReservations(@RequestParam("year")String year,@RequestParam("month")String month,@RequestParam("day")String day,@RequestParam("newReservations")int newReservations){
        orderSettingService.editReservations(year,month,day,newReservations);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS);

    }
}
