package org.example.services;

import org.example.po.GoodsPo;

import java.util.List;

public interface GoodsService {
    List<GoodsPo> searchProduct(String keyword, String name);

    List<GoodsPo> searchCategory(String categoryName);
}
