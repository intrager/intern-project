package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserResultDto {
    /**
     * 상태값 반환
     */
    private boolean success;
    private int code;
    private String msg;
}
