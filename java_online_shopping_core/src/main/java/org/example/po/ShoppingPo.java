package org.example.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("shopping")
@Builder
public class ShoppingPo {

    @TableId(type= IdType.AUTO)
    private Integer shoppingId;

    private String orderId;

    private String userId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

}
