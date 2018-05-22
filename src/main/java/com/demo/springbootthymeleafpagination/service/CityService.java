package com.demo.springbootthymeleafpagination.service;

import com.demo.springbootthymeleafpagination.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<City> listAllByPage(Pageable pageable);
}
