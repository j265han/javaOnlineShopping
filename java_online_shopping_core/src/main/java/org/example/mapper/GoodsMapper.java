package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.po.GoodsPo;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("<script> " +
            "SELECT * from sh_goods_basic a" +
            "<where> " +
            "            <if test=\"Name != null and Name != ''\"> " +
            "                 and keyword like concat('%',#{Name}, '%') or name like concat('%', #{Name}, '%')" +
            "           </if> " +
            "            </where>" +
            "</script>")
    List<GoodsPo> searchProduct(@Param("keyword") String keyword, @Param("Name") String name);

    @Select("<script> " +
            "SELECT a.* from sh_goods_basic a join home_page b on a.category_id = b.id" +
            "<where> " +
            "            <if test=\"categoryName != null and categoryName != ''\"> " +
            "                 and b.category_name = #{categoryName}" +
            "           </if> " +
            "            </where>" +
            "</script>")
    List<GoodsPo> searchCategory(@Param("categoryName") String categoryName);
}
