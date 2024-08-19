package org.example.services;

import org.example.req.OrderReq;
import org.example.resp.OrderResp;

import java.util.List;

public interface OrderService {

    void deleteCart(String userId, List<Integer> goodId);

    void buildOrderDirect(OrderReq orderReq);

    void updateStatus(String orderId, Integer status);

    void buildOrderCart(OrderReq orderReq);

    OrderResp getOrderDetail(String orderId);

    List<List<OrderResp>> getOrderDetailNew(String userId);

    List<String> getOrderIds(String userId);

    void deleteOrder(String orderId);

}
