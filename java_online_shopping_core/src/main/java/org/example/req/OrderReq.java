package org.example.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderReq {

    private String orderId;

    private Integer status;

    private String userId;

    private List<Integer> goodId;

    private String goodName;

    private String goodImage;

    private BigDecimal currentUnitPrice;

    private BigDecimal totalPrice;

    private Integer quantity;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;
}
