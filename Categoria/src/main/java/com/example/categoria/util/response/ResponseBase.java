package com.example.categoria.util.response;

import lombok.*;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase {

    private int code;
    private String message;
    private Optional data;

}
