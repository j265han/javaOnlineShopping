package org.example.controller;

import org.example.po.CartPo;
import org.example.po.OrderPo;
import org.example.po.RespCodePo;
import org.example.po.UserPo;
import org.example.req.AddrReq;
import org.example.req.OrderReq;
import org.example.resp.OrderResp;
import org.example.services.CartService;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/detail")
    public RespCodePo detail(@RequestParam String orderId) {
        OrderResp orderResp = orderService.getOrderDetail(orderId);
        return RespCodePo.success(orderResp);
    }

//    @GetMapping("/list")
//    public RespCodePo list(@RequestParam String userId) {
//        List<String> orderIds = orderService.getOrderIds(userId);
//        List<List<OrderResp>> orderResps = new ArrayList<>();
//        orderIds.forEach(s -> {
//            OrderResp orderResp =  orderService.getOrderDetail(s);
//            orderResps.add(Collections.singletonList(orderResp));
//        });
////
//        return RespCodePo.success(orderResps);
//    }

    @GetMapping("/list")
    public RespCodePo list(@RequestParam String userId) {
//        List<List<OrderResp>> orderResps = new ArrayList<>();
        List<List<OrderResp>> orderResp = orderService.getOrderDetailNew(userId);
//        orderResps.add(orderResp);
        return RespCodePo.success(orderResp);
    }

    @PostMapping("/build")
    public RespCodePo buildOrder(@RequestBody OrderReq orderReq ) {
        String userId = orderReq.getUserId();
        List<Integer> goodId = orderReq.getGoodId();

        String orderId = UUID.randomUUID().toString();
        orderReq.setOrderId(orderId);
        if (orderReq.getReceiverName() == null && orderReq.getReceiverAddress() == null && orderReq.getReceiverPhone() == null){
            UserPo userPo = cartService.getuserinfo(orderReq.getUserId());
            orderReq.setReceiverName(userPo.getUsername());
            orderReq.setReceiverAddress(userPo.getAddress());
            orderReq.setReceiverPhone(userPo.getPhoneNo());
        } else if (orderReq.getReceiverName() == null || orderReq.getReceiverAddress() == null || orderReq.getReceiverPhone() == null) {
            return RespCodePo.error("Missing Info");
        }

        orderService.buildOrderCart(orderReq);
        orderService.deleteCart(userId, goodId);
        return RespCodePo.success(orderId);

    }

    @PostMapping("/buildDirect")
    public RespCodePo buildOrderDirect(@RequestBody OrderReq orderReq) {
        String orderId = UUID.randomUUID().toString();
        orderReq.setOrderId(orderId);

        if (orderReq.getReceiverName() == null && orderReq.getReceiverAddress() == null && orderReq.getReceiverPhone() == null){
            UserPo userPo = cartService.getuserinfo(orderReq.getUserId());
            orderReq.setReceiverName(userPo.getUsername());
            orderReq.setReceiverAddress(userPo.getAddress());
            orderReq.setReceiverPhone(userPo.getPhoneNo());
        } else if (orderReq.getReceiverName() == null || orderReq.getReceiverAddress() == null || orderReq.getReceiverPhone() == null) {
            return RespCodePo.error("Missing Info");
        }

        orderService.buildOrderDirect(orderReq);
        return RespCodePo.success(orderId);

    }

    @PostMapping("/updateStatus")
    public RespCodePo updateStatus(@RequestBody OrderReq orderReq) {

        orderService.updateStatus(orderReq.getOrderId(), orderReq.getStatus());
        return RespCodePo.success();
    }

    @PostMapping("/delete")
    public RespCodePo deleteOrder(@RequestBody OrderReq orderReq) {

        orderService.deleteOrder(orderReq.getOrderId());
        return RespCodePo.success();
    }
}
