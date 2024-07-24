package org.example.controller;

import org.example.po.RespCodePo;
import org.example.po.ShoppingPo;
import org.example.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addr")
public class AddrController {

    @Autowired
    ShoppingService shoppingService;

    @PostMapping("add")
    public RespCodePo add(@RequestBody ShoppingPo po) {

        shoppingService.insertAddr(po);

        return RespCodePo.success();
    }
}
