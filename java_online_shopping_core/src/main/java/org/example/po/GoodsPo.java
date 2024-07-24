package org.example.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@TableName("sh_goods_basic")
public class GoodsPo {

    @NotEmpty(message = "id cannot be empty")
    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String description;

    private String picture;

    private Integer categoryId;

    private String name;

    private String keyword;

    private BigDecimal price;

    private Integer stock;
}
