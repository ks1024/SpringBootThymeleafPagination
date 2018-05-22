package com.demo.springbootthymeleafpagination.controller;

import com.demo.springbootthymeleafpagination.domain.PagedList;
import com.demo.springbootthymeleafpagination.domain.City;
import com.demo.springbootthymeleafpagination.domain.Pager;
import com.demo.springbootthymeleafpagination.domain.User;
import com.demo.springbootthymeleafpagination.service.CityService;
import com.demo.springbootthymeleafpagination.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final int INIT_PAGE_SIZE = 5;
    private static final int INIT_PAGE_INDEX = 0;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    /**
     * Pagination with custom pagedList
     * @param pageSize
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUsers(@RequestParam("pageSize")Optional<Integer> pageSize,
                           @RequestParam("page")Optional<Integer> page,
                           Model model) {
        int evalPageSize = pageSize.orElse(INIT_PAGE_SIZE);
        int evalPageIndex = (page.orElse(0) < 1) ? INIT_PAGE_INDEX : page.get() - 1;
        logger.info("evalPageSize: {} evalPageIndex: {}", evalPageSize, evalPageIndex);
        PagedList<User> pagedList = new PagedList<>(evalPageSize, evalPageIndex);
        List<User> userList = userService.findAllUsers(evalPageSize, pagedList.getPageOffset());
        logger.info("user list: {}", userList);
        int totalCount = userService.getUsersCount();
        logger.info("totalCount: {}", totalCount);
        pagedList.setItemList(userList);
        pagedList.setTotalCount(totalCount);
        model.addAttribute("list", pagedList);
        model.addAttribute("pageSizes", PAGE_SIZES);
        return "users";
    }


    /**
     * 请求 /cities?page=0&size=5 即可，Spring会自动解析page和size参数，因为Pageable是PageRequest的接口类，但是page必须是从0开始
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    @ResponseBody
    public Object getCities(Pageable pageable) {
        Page<City> cities = cityService.listAllByPage(pageable);
        return cities;
    }

    /**
     * An another approach for easier pagination
     * @param pageSize
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String getCities(@RequestParam("pageSize") Optional<Integer> pageSize,
                            @RequestParam("page") Optional<Integer> page,
                            Model model) {
        int evalPageSize = pageSize.orElse(INIT_PAGE_SIZE);
        int evalPageIndex = (page.orElse(0) < 1) ? INIT_PAGE_INDEX : page.get() - 1;
        logger.info("evalPageSize: {} evalPageIndex: {}", evalPageSize, evalPageIndex);
        //创建Pageable
        Pageable pageable = PageRequest.of(evalPageIndex, evalPageSize);
        Page<City> cities = cityService.listAllByPage(pageable);
        Pager pager = new Pager(cities.getTotalPages(), cities.getNumber());
        model.addAttribute("cities", cities);
        model.addAttribute("pageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        return "cities";
    }

}
