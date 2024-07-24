package org.example.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("user_info")
public class LoginPo {
    private Integer id;

    private Long loginTime;

    private Long expireTime;

    //unique id
    private String uuid;

    private String accName;

}