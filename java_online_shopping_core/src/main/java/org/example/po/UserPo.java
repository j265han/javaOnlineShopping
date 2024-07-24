package org.example.po;

import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@TableName("user_info")
public class UserPo{

    @NotEmpty(message = "id cannot be empty")
    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    //unique id
    private String uuid;

    private String username;

    private String address;

    private String phoneNo;

    @JsonIgnore
    private String password;
}

