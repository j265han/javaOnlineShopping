package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.dto.CartDto;
import org.example.po.CartPo;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<CartPo> {

    @Select(
            "select * from cart a join sh_goods b on a.good_id = b.id join sh_goods_basic c on b.basic_id = c.id where a.user_id = #{userId}"
    )
    IPage<CartDto> merge(@Param("page") Page<CartDto> page, @Param("userId") String userId);

    @Select("SELECT quantity, id from cart where good_id = #{goodId} and user_id = #{userId}")
    CartPo select(Integer goodId, String userId);
}
