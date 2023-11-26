package com.charity.vo;

import com.charity.entity.Certification;
import com.charity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificationVo {
    private Certification certification;
    private User user;
}
