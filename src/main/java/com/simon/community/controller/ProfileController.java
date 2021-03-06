package com.simon.community.controller;

import com.simon.community.dto.PageDTO;
import com.simon.community.model.User;
import com.simon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            Model model,
            HttpServletRequest request,
            @PathVariable(name="action") String action,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null ){
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的'论点'");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新'观点'");
        }

        // 能通过用于去查询相应的论坛发布的帖子～
        PageDTO pageDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pageDTO_profile", pageDTO);
        return "profile";
    }
}
