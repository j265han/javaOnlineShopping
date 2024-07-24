package org.example.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("orders")
@Builder
public class OrderPo {

    @TableId
    private String orderId;

    private String userId;

    private String shoppingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private LocalDateTime sendTime;

    private LocalDateTime endTime;

    private LocalDateTime closeTime;

}
