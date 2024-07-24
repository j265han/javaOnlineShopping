package org.example.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartReq {

    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @NotBlank
    private String userId;

    @NotBlank
    private Integer goodId;

    @NotBlank
    private Integer quantity;

}
