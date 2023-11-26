package com.charity.vo;

import com.charity.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageVo {
    private Message message;
    private String userName;
}
