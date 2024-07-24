package org.example.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.example.po.SpecPo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("order_item")
@Builder
public class OrderItemPo {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String orderId;

    private String userId;

    private Integer goodId;

    private String goodName;

    private String goodImage;

    private BigDecimal currentUnitPrice;

    private BigDecimal totalPrice;

    private Integer quantity;

//    @Transient
//    private List<org.example.po.SpecPo> SpecList;

}
