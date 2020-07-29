package com.simon.community.mapper;

import com.simon.community.dto.QuestionDTO;
import com.simon.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creater, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creater}, #{tag}) ")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creater = #{user_id} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "user_id") Integer user_id,
                        @Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select("select count(1) from question where creater = #{user_id}")
    Integer countByUserId(@Param(value = "user_id") Integer user_id);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);
}
