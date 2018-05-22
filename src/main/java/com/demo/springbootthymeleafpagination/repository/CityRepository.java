package com.demo.springbootthymeleafpagination.repository;

import com.demo.springbootthymeleafpagination.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

}
