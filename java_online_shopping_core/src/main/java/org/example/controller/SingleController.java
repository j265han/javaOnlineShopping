package org.example.controller;

import org.example.po.RespCodePo;
import org.example.po.SinglePo;
import org.example.services.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class SingleController {

    @Autowired
    private SingleService singleService;

    @GetMapping("/search")
    public RespCodePo search( Integer id) {

        List<SinglePo> singlePos = singleService.search(id);

        return RespCodePo.success(singlePos);
    }
}
