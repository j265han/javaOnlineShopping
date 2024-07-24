package org.example.services;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.dto.CartDto;
import org.example.po.CartPo;
import org.example.po.UserPo;
import org.example.req.CartReq;

public interface CartService {
    IPage<CartDto> getPage(Page<CartDto> page, String userId);

    void insert(CartReq cartReq);

    void delete(Integer id);

    void update(CartReq cartReq);

    CartPo checkcart(Integer goodId, String userId);

    UserPo getuserinfo(String username);
}
