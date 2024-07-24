package org.example.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUtils<T> {

    private long allCount;

    private long allPages;

    private long pageNum;

    private long pageSize;

    private List<T> dataItems;

}