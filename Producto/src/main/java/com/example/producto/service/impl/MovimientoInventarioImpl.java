package com.example.producto.service.impl;

import com.example.producto.constants.Constants;
import com.example.producto.dao.MovimientoInventarioDao;
import com.example.producto.dao.ProductoDao;
import com.example.producto.entity.MovimientoInventarioEntity;
import com.example.producto.entity.ProductoEntity;
import com.example.producto.service.MovimientoInventarioService;
import com.example.producto.util.request.RequestMovimientoInventario;
import com.example.producto.util.response.ResponseBase;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioImpl implements MovimientoInventarioService {

    private final MovimientoInventarioDao movimientoInventarioDao;
    private  final ProductoDao productoDao;

    public MovimientoInventarioImpl(MovimientoInventarioDao movimientoInventarioDao, ProductoDao productoDao) {
        this.movimientoInventarioDao = movimientoInventarioDao;
        this.productoDao = productoDao;
    }


    @Override
    public ResponseBase allMovimientoInventario() {
        List<MovimientoInventarioEntity> allInventario = movimientoInventarioDao.findAll();
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE, Optional.of(allInventario));
    }


    @Override
    public ResponseBase getByIdMovimientoInventario(Long id) {
        Optional<MovimientoInventarioEntity>  inventarioEncontrado = movimientoInventarioDao.findById(id);
        if (inventarioEncontrado.isPresent())
        {

            return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE, inventarioEncontrado);

        }

        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE, inventarioEncontrado);
    }

    @Override
    public ResponseBase addMovimientoInventario(RequestMovimientoInventario inventario) {

        MovimientoInventarioEntity movimientoInventarioEntity = getMovimientoInventario(new MovimientoInventarioEntity(),inventario);
        movimientoInventarioEntity = movimientoInventarioDao.save(movimientoInventarioEntity);
        Optional<ProductoEntity > optionalProductoEntity= productoDao.findById(inventario.getId_producto());
        if (optionalProductoEntity.isPresent())
        {
            ProductoEntity producto = optionalProductoEntity.get();
            if ("entrada".equalsIgnoreCase(inventario.getTipo_movimiento()))
            {
                producto.setStock(producto.getStock() + inventario.getCantidad());
            }
            else {
                if (producto.getStock() >= inventario.getCantidad())
                {

                    producto.setStock(producto.getStock()- inventario.getCantidad());
                }
                else{
                    return new ResponseBase(Constants.ERROR_CODE, "No hay suficiente stock disponible", Optional.of(List.of()));
                }
            }
            productoDao.save(producto);
        }
        else {
            return  new ResponseBase(Constants.NOT_FOUND_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
        }
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(movimientoInventarioEntity));

    }

    public Timestamp getTimesTamp()
    {
        Long time = System.currentTimeMillis();
        Timestamp getTime =  new Timestamp(time);
        return  getTime;
    }


    public  MovimientoInventarioEntity getMovimientoInventario(MovimientoInventarioEntity movimientoInventarioEntity,RequestMovimientoInventario inventario)
    {

            movimientoInventarioEntity.setTipo_movimiento(inventario.getTipo_movimiento());
            movimientoInventarioEntity.setId_producto(inventario.getId_producto());
            movimientoInventarioEntity.setCantidad(inventario.getCantidad());
            movimientoInventarioEntity.setFecha_movimiento(getTimesTamp());
            movimientoInventarioEntity.setDate_create(getTimesTamp());

            return  movimientoInventarioEntity;
    }

}
