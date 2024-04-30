package com.example.cliente.service;

import com.example.cliente.util.request.RequestCliente;
import com.example.cliente.util.response.ResponseBase;

public interface ClienteService {

    ResponseBase allClientes();

    ResponseBase addCliente(RequestCliente requestCliente);

    ResponseBase seachCliente(Long id);


}
