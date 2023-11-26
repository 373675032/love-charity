package com.charity.vo;

import com.charity.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVo {
    private Article article;
    private String userName;
    private String activity;
    private Integer comment;
}
