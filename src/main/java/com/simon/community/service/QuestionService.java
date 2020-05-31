package com.simon.community.service;

import com.simon.community.dto.PageDTO;
import com.simon.community.dto.QuestionDTO;
import com.simon.community.mapper.QuestionMapper;
import com.simon.community.mapper.UserMapper;
import com.simon.community.model.Question;
import com.simon.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 分页逻辑
     * @param page：当前页
     * @param size：页面承载数据量
     * @return
     */
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        // 查询分页的总数
        Integer pageCount = questionMapper.count();
        pageDTO.setPagination(pageCount, page, size);
        // 参数验证：
        if (page < 1) {
            page = 1;
        }
        if (page > pageDTO.getTotalPage()) {
            page = pageDTO.getTotalPage();
        }
        Integer offset = size*(page-1);
        // 获取当前页（page）该展示论坛内容数据
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        // 遍历论坛内容，将用户信息添加到论坛内容list中
        for (Question question: questionList) {
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        // 将组织好的论坛内容放入DTO中
        pageDTO.setQuestionDTOS(questionDTOS);

        return pageDTO;
    }

    public PageDTO list(Integer user_id, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        // 查询分页的总数
        Integer pageCount = questionMapper.countByUserId(user_id);
        pageDTO.setPagination(pageCount, page, size);
        // 参数验证：
        if (page < 1) {
            page = 1;
        }
        if (page > pageDTO.getTotalPage()) {
            page = pageDTO.getTotalPage();
        }
        Integer offset = size*(page-1);
        // 获取当前页（page）该展示论坛内容数据
        List<Question> questionList = questionMapper.listByUserId(user_id, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        // 遍历论坛内容，将用户信息添加到论坛内容list中
        for (Question question: questionList) {
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        // 将组织好的论坛内容放入DTO中
        pageDTO.setQuestionDTOS(questionDTOS);

        return pageDTO;
    }
}
