package com.fmall.dao;

import com.fmall.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import redis.clients.jedis.BinaryClient;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> getByOrderIdAndUserId(@Param("orderNO") Long orderNo, @Param("userId") Integer userId);

    void batchInsert(@Param("orderItemList") List<OrderItem> orderItemList);

    List<OrderItem> getByOrderNo(@Param("orderNO") Long orderNo);
}