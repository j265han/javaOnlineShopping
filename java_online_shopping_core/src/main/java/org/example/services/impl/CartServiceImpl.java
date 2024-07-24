package org.example.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.dto.CartDto;
import org.example.mapper.CartMapper;
import org.example.mapper.SingleMapper;
import org.example.mapper.UserMapper;
import org.example.po.CartPo;
import org.example.po.SpecPo;
import org.example.po.UserPo;
import org.example.req.CartReq;
import org.example.services.CartService;
import org.example.services.UserService;
import org.example.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Resource
    private SingleMapper singleMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<CartDto> getPage(Page<CartDto> page, String userId) {

        IPage<CartDto> pageList = cartMapper.merge(page, userId);

        List<Integer> goodsId = pageList.getRecords().stream().map(CartDto::getGoodId).collect(Collectors.toList());

        if (goodsId.isEmpty()) {
            pageList.setRecords(null) ;
        } else {
            List<SpecPo> specPoList = singleMapper.search_spec(goodsId);

            Map<Integer, List<SpecPo>> specPoMap = specPoList.stream().collect(Collectors.groupingBy(SpecPo::getGoodsId));

            pageList.getRecords().forEach(cartDto -> {
                cartDto.setSpecList(specPoMap.get(cartDto.getGoodId()));
            });
        }

        return pageList;
    }


    @Override
    public CartPo checkcart(Integer goodId, String userId) {

        return cartMapper.select(goodId, userId);

    }

    @Override
    public UserPo getuserinfo(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void insert(CartReq cartReq) {
        CartPo cartPo = CopyUtils.copyAndObtain(cartReq,CartPo.class);
        cartMapper.insert(cartPo);
    }

    @Override
    public void delete(Integer id) {

        cartMapper.deleteById(id);
    }

    @Override
    public void update(CartReq cartReq) {

        CartPo cartPo = CopyUtils.copyAndObtain(cartReq,CartPo.class);
        cartPo.setUpdatedTime(LocalDateTime.now());
        cartMapper.updateById(cartPo);

    }


}
