package com.example.categoria.service;

import com.example.categoria.util.request.RequestCategoria;
import com.example.categoria.util.response.ResponseBase;

public interface CategoriaService {

    ResponseBase findAllCategoria();
    ResponseBase findOneCategoriaRedis(Long id);

    ResponseBase findOneCategoria(Long id);

    ResponseBase createCategoria(RequestCategoria requestCategoria);

    ResponseBase updateCategoria(Long id, RequestCategoria requestCategoria);

}
