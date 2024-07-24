package org.example.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.dto.OrderItemDto;
import org.example.mapper.*;
import org.example.po.*;
import org.example.req.OrderReq;
import org.example.resp.OrderResp;
import org.example.services.OrderService;
import org.example.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    SingleMapper singleMapper;

    @Override
    public void buildOrderCart(OrderReq orderReq) {

        String userId = orderReq.getUserId();
        String orderId = orderReq.getOrderId();
        List<Integer> goodId = orderReq.getGoodId();
        String name = orderReq.getReceiverName();
        String addr = orderReq.getReceiverAddress();
        String phone = orderReq.getReceiverPhone();

        OrderPo orderPo = OrderPo.builder().userId(userId).orderId(orderId).build();
        orderMapper.insert(orderPo);

        goodId.forEach(s -> {
            OrderItemPo orderItemPo = OrderItemPo.builder().userId(userId).orderId(orderId).goodId(s).build();

            orderItemMapper.insert(orderItemPo);
            orderItemMapper.insertInfo(s);
            orderItemMapper.updateTotal(userId, s);

        });

        ShoppingPo shoppingPo = ShoppingPo.builder()
                                        .userId(userId)
                                        .orderId(orderId)
                                        .receiverPhone(phone)
                                        .receiverAddress(addr)
                                        .receiverName(name)
                                        .build();
        shoppingMapper.insert(shoppingPo);
    }

    @Override
    public OrderResp getOrderDetail(String orderId) {

        List<OrderItemPo> orderItemPos = orderMapper.getItemsDetail(orderId);

        OrderResp orderResp = orderMapper.getOrderDetail(orderId);

        List<Integer> goodsId = orderItemPos.stream().map(OrderItemPo::getGoodId).collect(Collectors.toList());
        List<SpecPo> specPoList = singleMapper.search_spec(goodsId);
        Map<Integer, List<SpecPo>> specPoMap = specPoList.stream().collect(Collectors.groupingBy(SpecPo::getGoodsId));

        List<OrderItemDto> dtoList = new ArrayList<>();
        orderItemPos.forEach(singlePo -> {
            OrderItemDto dto = CopyUtils.copyAndObtain(singlePo, OrderItemDto.class);
            dto.setSpecList(specPoMap.get(singlePo.getGoodId()));
            dtoList.add(dto);
        });

        orderResp.setItemsDetails(dtoList);
        return orderResp;
    }

    @Override
    public List<String> getOrderIds(String userId) {
        return orderMapper.getOrderIds(userId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderMapper.deleteByOrderId(orderId);
        orderItemMapper.deleteByOrderId(orderId);
        shoppingMapper.deleteByOrderId(orderId);
    }

    @Override
    public void deleteCart(String userId, List<Integer> goodId) {
        LambdaQueryWrapper<CartPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartPo::getUserId, userId).in(CartPo::getGoodId, goodId);

        cartMapper.delete(wrapper);
    }

    @Override
    public void buildOrderDirect(OrderReq orderReq) {
        BigDecimal quantity = new BigDecimal( Integer.parseInt ( orderReq.getQuantity().toString() ) );
        OrderItemPo orderItemPo = OrderItemPo.builder()
                                            .orderId(orderReq.getOrderId())
                                            .userId(orderReq.getUserId())
                                            .goodId(orderReq.getGoodId().get(0))
//                                            .goodName(orderReq.getGoodName())
//                                            .goodImage(orderReq.getGoodImage())
//                                            .currentUnitPrice(orderReq.getCurrentUnitPrice())
//                                            .quantity(orderReq.getQuantity())
//                                            .totalPrice(orderReq.getCurrentUnitPrice().multiply(quantity))
                                            .build();
        OrderPo orderPo = OrderPo.builder().orderId(orderReq.getOrderId()).userId(orderReq.getUserId()).build();
        orderMapper.insert(orderPo);
        orderItemMapper.insert(orderItemPo);
        orderItemMapper.insertInfoDirect(orderReq.getGoodId().get(0), orderReq.getQuantity());
        orderItemMapper.updateTotal(orderReq.getUserId(), orderReq.getGoodId().get(0));
        ShoppingPo shoppingPo = ShoppingPo.builder()
                .userId(orderReq.getUserId())
                .orderId(orderReq.getOrderId())
                .receiverPhone(orderReq.getReceiverPhone())
                .receiverAddress(orderReq.getReceiverAddress())
                .receiverName(orderReq.getReceiverName())
                .build();
        shoppingMapper.insert(shoppingPo);
    }

    @Override
    public void updateStatus(String orderId, Integer status) {

        orderMapper.updateStatus(orderId, status);
    }

}
