package org.example.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@TableName("cart")
public class CartPo {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String userId;

    private Integer goodId;

    private Integer quantity;

}
