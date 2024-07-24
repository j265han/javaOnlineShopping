package org.example.services.impl;

import org.example.mapper.GoodsMapper;
import org.example.po.GoodsPo;
import org.example.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsPo> searchProduct(String keyword, String name) {
        return goodsMapper.searchProduct(keyword, name);
    }

    @Override
    public List<GoodsPo> searchCategory(String categoryName) {
        return goodsMapper.searchCategory(categoryName);
    }


}
