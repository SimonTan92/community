package com.simon.community.dto;

import com.simon.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creater;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
