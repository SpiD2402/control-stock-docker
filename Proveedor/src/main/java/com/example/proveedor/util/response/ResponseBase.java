package com.example.proveedor.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase {

    private int code;
    private String message;
    private Optional  data ;


}
