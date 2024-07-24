package org.example.resp;

import lombok.Data;
import org.example.dto.OrderItemDto;
import org.example.po.OrderItemPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResp {

    String orderId;

    Integer status;

    String receiverName;

    String receiverPhone;

    String receiverAddress;

    LocalDateTime createdTime;

//    List<OrderItemPo> itemsDetails;

    List<OrderItemDto> itemsDetails;

}
