package com.example.producto.service;

import com.example.producto.util.request.RequestMovimientoInventario;
import com.example.producto.util.response.ResponseBase;

public interface MovimientoInventarioService {


    ResponseBase allMovimientoInventario();

    ResponseBase getByIdMovimientoInventario(Long id);

    ResponseBase addMovimientoInventario(RequestMovimientoInventario inventario);




}
