package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {

    void add(List<OrderSetting> orderSettings);

    List<OrderSetting> getReservations(String date);

    void editReservations(String year, String month, String day,int newReservations);
}
