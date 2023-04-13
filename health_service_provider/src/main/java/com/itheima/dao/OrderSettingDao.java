package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {
    void add(@Param("date") String dateString, @Param("number")int number, @Param("reservations")int reservations);

    List<OrderSetting> getReservations(@Param("startDay") String startDay,@Param("endDay") String endDay);

    OrderSetting queryReservations(@Param("date") String dateString, int newReservations);

    void editReservations(@Param("date") String dateString,@Param("newReservations") int newReservations);

    void addReservations(@Param("date") String dateString, @Param("newReservations")int newReservations);
}
