package org.example.services.impl;

import org.example.mapper.CategoryMapper;
import org.example.po.CategoryPo;
import org.example.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryPo> page() {
        return categoryMapper.page();
    }
}
