package com.simon.community.service;

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

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question: questionList) {
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
