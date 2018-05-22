package com.demo.springbootthymeleafpagination.controller;

import com.demo.springbootthymeleafpagination.domain.PagedList;
import com.demo.springbootthymeleafpagination.domain.User;
import com.demo.springbootthymeleafpagination.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object getUsers(@RequestParam("pageSize")Optional<Integer> pageSize,
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
}
