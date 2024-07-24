package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.po.OrderItemPo;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemPo> {

    @Update("UPDATE order_item a set a.good_name = (select name from sh_goods where sh_goods.id = #{goodId})," +
            " a.good_image = (select picture from sh_goods where sh_goods.id = #{goodId}), " +
            "a.current_unit_price = (select price from sh_goods where sh_goods.id = #{goodId}), " +
            "a.quantity = (select quantity from cart where cart.good_id = #{goodId}) " +
            "where a.good_id = #{goodId}")
    void insertInfo(Integer goodId);

    @Update("UPDATE order_item a set a.good_name = (select name from sh_goods where sh_goods.id = #{goodId})," +
            " a.good_image = (select picture from sh_goods where sh_goods.id = #{goodId}), " +
            "a.current_unit_price = (select price from sh_goods where sh_goods.id = #{goodId}), " +
            "a.quantity = #{quantity} ")
    void insertInfoDirect(Integer goodId, Integer quantity);

    @Update("UPDATE order_item a set a.total_price = a.current_unit_price * a.quantity where a.user_id = #{userId} and a.good_id = #{s}")
    void updateTotal(String userId, Integer s);

    @Delete("DELETE FROM order_item a WHERE a.order_id = #{orderId}")
    void deleteByOrderId(String orderId);
}
