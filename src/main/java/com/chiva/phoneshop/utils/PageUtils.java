package com.chiva.phoneshop.utils;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PageUtils {
    int SIZE_DEFAULT = 10;
    int PAGE_DEFAULT = 1;
    String PAGE_KEY = "_page";
    String SIZE_KEY = "_size";

    static Pageable getPageable(Map<String, String> params) {
        int page = MapUtils.getIntValue(params, PAGE_KEY, PAGE_DEFAULT);
        int size = MapUtils.getIntValue(params, SIZE_KEY, SIZE_DEFAULT);
        return PageRequest.of(page - 1, size);
    }
}
