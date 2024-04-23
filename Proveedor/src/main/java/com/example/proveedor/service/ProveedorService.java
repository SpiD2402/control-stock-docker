package com.example.proveedor.service;

import com.example.proveedor.util.request.RequestProveedor;
import com.example.proveedor.util.response.ResponseBase;

public interface ProveedorService {


    ResponseBase findAllProveedor();
    ResponseBase findOneProveedor(Long id);



    ResponseBase createProveedor(RequestProveedor requestProveedor);

    ResponseBase updateProveedor(Long id, RequestProveedor requestProveedor);

    ResponseBase deleteProveedor(Long id);



}
