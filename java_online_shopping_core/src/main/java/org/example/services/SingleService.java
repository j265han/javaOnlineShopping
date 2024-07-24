package org.example.services;


import org.example.po.SinglePo;

import java.util.List;

public interface SingleService {
    List<SinglePo> search(Integer id);
}
