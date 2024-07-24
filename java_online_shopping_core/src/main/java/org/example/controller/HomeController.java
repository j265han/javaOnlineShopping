package org.example.controller;


import org.example.po.CategoryPo;
import org.example.po.GoodsPo;
import org.example.po.RespCodePo;
import org.example.services.GoodsService;
import org.example.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/pages")
    public RespCodePo homepage(){
        List<CategoryPo> categoryPos = homeService.page();
        CategoryPo categoryPo = CategoryPo.buildCategoryTree(categoryPos);
        if (ObjectUtils.isEmpty(categoryPo)) {
            return RespCodePo.success();
        }
        return RespCodePo.success(categoryPo.getChildren());
    }

    @GetMapping("/search")
    public RespCodePo search(String keyword, String categoryName, String name){
        if(categoryName == null){
            List<GoodsPo> goodsPos = goodsService.searchProduct(keyword, name);
            return RespCodePo.success(goodsPos);
        } else {
            List<GoodsPo> goodsPos = goodsService.searchCategory(categoryName);
            return RespCodePo.success(goodsPos);
        }

    }
}
