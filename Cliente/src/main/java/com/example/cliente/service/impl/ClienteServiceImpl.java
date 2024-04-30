package com.example.cliente.service.impl;

import com.example.cliente.constants.Constants;
import com.example.cliente.dao.ClienteDao;
import com.example.cliente.entity.ClienteEntity;
import com.example.cliente.service.ClienteService;
import com.example.cliente.util.request.RequestCliente;
import com.example.cliente.util.response.ResponseBase;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private  final ClienteDao clienteDao;

    public ClienteServiceImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public ResponseBase allClientes() {
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE, Optional.of(clienteDao.findAll()));
    }

    @Override
    public ResponseBase addCliente(RequestCliente requestCliente) {
        ClienteEntity cliente = getClient(new ClienteEntity(),requestCliente,false);
        cliente  =  clienteDao.save(cliente);
        return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(cliente));
    }

    @Override
    public ResponseBase seachCliente(Long id) {
        Optional<ClienteEntity> cliente = clienteDao.findById(id);
        if (cliente.isPresent())
        {
            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,cliente);
        }
        return  new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
    }

    public Timestamp timestamp()
    {
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        return  timestamp;
    }


    public ClienteEntity getClient(ClienteEntity cliente,RequestCliente requestCliente,boolean isUpdate)
    {
        cliente.setDireccion(requestCliente.getDireccion());
        cliente.setNombre(requestCliente.getNombre());
        cliente.setEmail(requestCliente.getEmail());
        cliente.setTelefono(requestCliente.getTelefono());
        if (isUpdate)
        {
            cliente.setDate_modify(timestamp());

        }
        else
        {
            cliente.setDate_create(timestamp());
        }

        return  cliente;


    }

}
