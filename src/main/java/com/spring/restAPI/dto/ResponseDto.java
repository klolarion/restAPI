package com.spring.restAPI.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<E> {
    private int resultCode;
    private String body;
    private E entity;
}
