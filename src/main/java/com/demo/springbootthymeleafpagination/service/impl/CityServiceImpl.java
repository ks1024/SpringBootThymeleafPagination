package com.demo.springbootthymeleafpagination.service.impl;

import com.demo.springbootthymeleafpagination.domain.City;
import com.demo.springbootthymeleafpagination.repository.CityRepository;
import com.demo.springbootthymeleafpagination.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public Page<City> listAllByPage(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
}
