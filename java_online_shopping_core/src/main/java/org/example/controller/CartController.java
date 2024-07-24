package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.dto.CartDto;
import org.example.po.CartPo;
import org.example.po.RespCodePo;
import org.example.po.UserPo;
import org.example.req.AddrReq;
import org.example.req.CartReq;
import org.example.req.UserReq;
import org.example.services.CartService;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.example.utils.PageUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/page")
    public PageUtils<CartDto> paginquery(@RequestParam String userId){

        Page<CartDto> page = new Page<>(1, 10);

        IPage<CartDto> respPage = cartService.getPage(page, userId);

        PageUtils<CartDto> pageResult = new PageUtils<>();
        pageResult.setPageNum(respPage.getCurrent());
        pageResult.setPageSize(respPage.getSize());
        pageResult.setAllPages(respPage.getPages());
        if(respPage.getRecords() == null) {
            pageResult.setAllCount(0);
        } else {
            pageResult.setAllCount(respPage.getRecords().size());
        }

        List<CartDto> respList = respPage.getRecords();

        pageResult.setDataItems(respList);

        return pageResult;
    }

    @PostMapping("/add")
    public RespCodePo add(@RequestBody CartReq cartReq){

        CartPo po = cartService.checkcart(cartReq.getGoodId(), cartReq.getUserId());

        if (po == null){
            cartService.insert(cartReq);

            return RespCodePo.success("Added to cart");
        } else {

            cartReq.setQuantity(po.getQuantity() + cartReq.getQuantity());
            cartReq.setId(po.getId());
            cartService.update(cartReq);

            return RespCodePo.success("Updated Success");
        }

    }

    @PostMapping("/delete")
    public RespCodePo delete(@RequestBody  CartReq cartReq){

        cartService.delete(cartReq.getId());

        return RespCodePo.success("Deleted from Cart");
    }

    @PostMapping("/update")
    public RespCodePo update(@RequestBody CartReq cartReq){

        cartService.update(cartReq);

        return RespCodePo.success("Updated Success");
    }

    @PostMapping("/confirmInfo")
    public RespCodePo confirmInfo(@RequestBody UserReq userReq){

        UserPo userinfo = cartService.getuserinfo(userReq.getUsername());

        return RespCodePo.success(Collections.singletonList(userinfo));

    }
}

