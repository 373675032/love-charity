package com.charity.vo;

import com.charity.entity.Feedback;
import com.charity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackVo {
    private Feedback feedback;
    private User user;
}
