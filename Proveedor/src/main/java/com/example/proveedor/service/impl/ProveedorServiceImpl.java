package com.example.proveedor.service.impl;

import com.example.proveedor.constants.Constants;
import com.example.proveedor.dao.ProveedorDao;
import com.example.proveedor.entity.ProveedorEntity;
import com.example.proveedor.service.ProveedorService;
import com.example.proveedor.util.request.RequestProveedor;
import com.example.proveedor.util.response.ResponseBase;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl  implements ProveedorService {


    private final ProveedorDao proveedorDao;

    public ProveedorServiceImpl(ProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;
    }

    @Override
    public ResponseBase findAllProveedor() {
        List<ProveedorEntity> proveedorList = proveedorDao.findAll().stream().filter(proveedor -> proveedor.getEstado()==1).collect(Collectors.toList());
        if (proveedorList.isEmpty())
        {
            return new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));

        }
        else {
            int count =  proveedorList.size();
            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(List.of(Optional.of(proveedorList), Map.of("count",count))));
        }
    }

    @Override
    public ResponseBase findOneProveedor(Long id) {
        Optional<ProveedorEntity>proveedorOne = proveedorDao.findById(id);
        if (proveedorOne.isPresent())
        {
            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,proveedorOne);
        }
        else {

            return  new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
        }
    }

    @Override
    public ResponseBase createProveedor(RequestProveedor requestProveedor) {
        ProveedorEntity proveedor = getProveedor(new ProveedorEntity(),requestProveedor,false);
        proveedorDao.save(proveedor);
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(proveedor));
    }

    @Override
    public ResponseBase updateProveedor(Long id, RequestProveedor requestProveedor) {
        Optional<ProveedorEntity>proveedorOne = proveedorDao.findById(id);
        if (proveedorOne.isPresent())
        {
            ProveedorEntity proveedor  = proveedorOne.get();
            proveedor = getProveedor(proveedor,requestProveedor,true);
            proveedorDao.save(proveedor);
            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(proveedor));
        }
        else {

            return  new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
        }
    }

    @Override
    public ResponseBase deleteProveedor(Long id) {
        Optional<ProveedorEntity>proveedorOne = proveedorDao.findById(id);
        if (proveedorOne.isPresent()) {
            ProveedorEntity proveedor = proveedorOne.get();
            proveedor.setDate_delete(timestamp());
            proveedor.setEstado(0);
            proveedorDao.save(proveedor);

            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(proveedor));
        }
        else {
            return  new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
        }


    }

    public Timestamp timestamp()
    {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }


    public ProveedorEntity getProveedor(ProveedorEntity proveedor,RequestProveedor requestProveedor,boolean isUpdate)
    {
        proveedor.setNombre(requestProveedor.getNombre());
        proveedor.setDireccion(requestProveedor.getDireccion());
        proveedor.setEmail(requestProveedor.getEmail());
        proveedor.setTelefono(requestProveedor.getTelefono());
        proveedor.setEstado(1);
        if (isUpdate)
        {
            proveedor.setDate_modify(timestamp());
        }
        else {
            proveedor.setDate_create(timestamp());

        }
        return  proveedor;
    }





}
