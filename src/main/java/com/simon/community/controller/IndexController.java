package com.simon.community.controller;

import com.simon.community.dto.PageDTO;
import com.simon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

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
        return "index";
    }
}
