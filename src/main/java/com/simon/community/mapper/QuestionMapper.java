package com.simon.community.mapper;

import com.simon.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creater, tag) " +
            "values(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creater}, #{tag}) ")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
