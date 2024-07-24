package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.po.CategoryPo;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * from home_page")
    List<CategoryPo> page();
}
