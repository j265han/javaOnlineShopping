package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.po.SinglePo;
import org.example.po.SpecPo;

import java.util.List;

@Mapper
public interface SingleMapper {

    @Select(
           "Select a.* from sh_goods a where a.basic_id = #{id}"
    )
    List<SinglePo> search_basic(Integer id);

    @Select(
        "<script>" +
            "Select b.goods_id, c.name as spec_item, d.name as spec from sh_goods_spec_set b left join sh_goods_spec_item c on b.spec_item_id = c.id left join sh_goods_spec d on c.spec_id = d.id  where b.goods_id in" +
                "    <foreach collection='goodsId' item='item' open='(' separator=',' close=')'>" +
                "       #{item}" +
                "    </foreach>" +
        "</script>"
    )
    List<SpecPo> search_spec(@Param("goodsId") List<Integer> goodsId);
}
