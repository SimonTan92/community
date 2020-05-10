package com.simon.community.controller;

import com.simon.community.dto.PageDTO;
import com.simon.community.mapper.UserMapper;
import com.simon.community.model.User;
import com.simon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        // 查询所有index页面上的数据，论坛内容以及分页内容
        PageDTO pageDTO = questionService.list(page, size);
        model.addAttribute("pageDTO", pageDTO);
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return "index";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                // 获取user对象
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
