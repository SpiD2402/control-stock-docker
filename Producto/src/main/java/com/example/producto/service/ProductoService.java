package com.example.producto.service;


import com.example.producto.util.request.RequestProducto;
import com.example.producto.util.response.ResponseBase;

public interface ProductoService {


    ResponseBase clientByIdProveedor(Long id);

    ResponseBase clientByIdCategoria(Long id);

    ResponseBase allProducto();

    ResponseBase addProducto(RequestProducto requestProducto);

    ResponseBase searchNameProducto(String name);

    ResponseBase findOneProducto (Long id);




}
