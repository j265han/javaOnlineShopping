package org.example.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@TableName("sh_goods")
public class SinglePo {

    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Integer basicId;

    private String name;

    private String keyword;

    private BigDecimal price;

    private Integer quantity;

    private Integer stock;

   private List<SpecPo> SpecList;
}
