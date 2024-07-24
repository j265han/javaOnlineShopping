package org.example.dto;

import lombok.Data;
import org.example.po.SpecPo;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemDto {

    private Integer id;

    private String orderId;

    private String userId;

    private Integer goodId;

    private String goodName;

    private String goodImage;

    private BigDecimal currentUnitPrice;

    private BigDecimal totalPrice;

    private Integer quantity;

    private List<SpecPo> SpecList;
}
