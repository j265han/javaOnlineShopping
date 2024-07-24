package org.example.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespCodePo<T> {
    private Integer code;
    private String message;
    private T data;

    public static <E> RespCodePo<E> success(E data){
        return new RespCodePo<>(1, "success", data);
    }
    public static RespCodePo success(){
        return new RespCodePo<>(1, "success", null);
    }
    public static RespCodePo error(String message){
        return new RespCodePo<>(0, message, null);
    }
}
