package org.example.dto;

import lombok.Data;
import lombok.ToString;
import org.example.po.SpecPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
public class CartDto {
    private String id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String userId;

    private Integer goodId;

    private Integer quantity;

    private String name;

    private String keyword;

    private BigDecimal price;

    private Integer stock;

    private List<SpecPo> SpecList;
}
