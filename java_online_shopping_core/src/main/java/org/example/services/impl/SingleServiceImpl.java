package org.example.services.impl;

import org.example.mapper.SingleMapper;
import org.example.po.SinglePo;
import org.example.po.SpecPo;
import org.example.services.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SingleServiceImpl implements SingleService {

    @Autowired
    SingleMapper singleMapper;

    @Override
    public List<SinglePo> search(Integer id) {
        List<SinglePo> basicList = singleMapper.search_basic(id);

        List<Integer> goodsId = basicList.stream().map(SinglePo::getId).collect(Collectors.toList());

        List<SpecPo> specPoList = singleMapper.search_spec(goodsId);

        Map<Integer, List<SpecPo>> specPoMap = specPoList.stream().collect(Collectors.groupingBy(SpecPo::getGoodsId));

        basicList.forEach(singlePo -> {
            singlePo.setSpecList(specPoMap.get(singlePo.getId()));
            singlePo.setQuantity(1);
        });

        return basicList;
    }
}
