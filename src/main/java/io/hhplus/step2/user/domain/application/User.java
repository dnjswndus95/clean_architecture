package io.hhplus.step2.user.domain.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class User {
    private Long id;
    private Date createDate;
    private Date updateDate;
}
