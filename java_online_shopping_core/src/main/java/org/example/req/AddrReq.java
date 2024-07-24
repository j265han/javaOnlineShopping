package org.example.req;

import lombok.Data;

@Data
public class AddrReq {

    private String orderId;

    private String addr;

    private Integer phone;

    private String receiverName;

    private Integer receiverPhone;

    private String receiverAddr;
}
