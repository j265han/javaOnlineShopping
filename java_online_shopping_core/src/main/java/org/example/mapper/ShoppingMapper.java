package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.example.po.ShoppingPo;

@Mapper
public interface ShoppingMapper extends BaseMapper<ShoppingPo> {

    @Delete("DELETE FROM shopping a WHERE a.order_id = #{orderId}")
    void deleteByOrderId(String orderId);
}
