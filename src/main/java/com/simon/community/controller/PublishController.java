package com.simon.community.controller;

import com.simon.community.mapper.QuestionMapper;
import com.simon.community.model.Question;
import com.simon.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        // 先留存之前的数据，出了错也不能让人家白写呀
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        // 数据校验
        // 嘤嘤嘤。。没有前端只能自己做校验。。。
        if ("".equals(title) || title == null) {
            model.addAttribute("error", "发布标题不能为空！！！");
            return "/publish";
        }
        if ("".equals(description) || description == null) {
            model.addAttribute("error", "发布内容不能为空！！！");
            return "/publish";
        }
        // 暂存标签不能为空，后续修改逻辑
        if ("".equals(tag) || tag == null) {
            model.addAttribute("error", "标签不能为空！！！");
            return "/publish";
        }
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreater(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
