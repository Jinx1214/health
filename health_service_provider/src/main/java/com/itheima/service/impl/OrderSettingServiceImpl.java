package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utilities.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        //判断是否为空
        if (orderSettings != null && orderSettings.size()>0){
            for (OrderSetting orderSetting : orderSettings) {
                orderSettingDao.add(new SimpleDateFormat("yyyy/MM/dd").format(orderSetting.getOrderDate()),orderSetting.getNumber(),orderSetting.getReservations());
            }

        }

    }

    @Override
    public List<OrderSetting> getReservations(String date) {
        //选择月份对应的天数
        //只需要查询当月的数据即可
        String endDay = TimeUtils.getTime(date);
        String startDay =  date + "-1";
        List<OrderSetting> list = orderSettingDao.getReservations(startDay,endDay);
        return list;
    }

    @Override
    public void editReservations(String year, String month, String day,int newReservations) {
        //先查询 在更改 如果有预约信息则进行更新否则进行添加
        Date date = new Date(year + "/" + month + "/" + day);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
          OrderSetting count = orderSettingDao.queryReservations(format,newReservations);
        if(count!=null){
            orderSettingDao.editReservations(format,newReservations);
        }else {
            orderSettingDao.addReservations(format,newReservations);

        }
    }
}
