package com.simon.community.dto;

// DTO: Data Transfer Object ：数据传输对象
// 一般参数超过两个，最好封装成对象

import lombok.Data;

@Data
public class AccessTokenDTO {

    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;
}
