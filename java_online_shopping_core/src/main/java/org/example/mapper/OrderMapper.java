package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.po.OrderItemPo;
import org.example.po.OrderPo;
import org.example.resp.OrderResp;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<OrderPo> {

    @Delete("DELETE FROM orders a WHERE a.order_id = #{orderId}")
    void deleteByOrderId(String orderId);

    @Select("select a.status, b.* from orders a join shopping b on a.order_id=b.order_id where a.order_id = #{orderId}")
    OrderResp getOrderDetail(String orderId);

    @Select("select  c.* from order_item c where c.order_id = #{orderId}")
    List<OrderItemPo> getItemsDetail(String orderId);

    @Select("select a.order_id from orders a where a.user_id = #{userId}")
    List<String> getOrderIds(String userId);

    @Update("Update orders a set status = #{status} where a.order_id = #{orderId} ")
    void updateStatus(String orderId, Integer status);
}
