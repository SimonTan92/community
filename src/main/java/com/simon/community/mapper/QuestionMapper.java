package com.simon.community.mapper;

import com.simon.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creater, tag) " +
            "values(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creater}, #{tag}) ")
    void create(Question question);
}
