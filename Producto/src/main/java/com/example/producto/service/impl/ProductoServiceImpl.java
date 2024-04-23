package com.example.producto.service.impl;

import com.example.producto.clients.CategoriaClientRest;
import com.example.producto.clients.ProveedorClientRest;
import com.example.producto.constants.Constants;
import com.example.producto.dao.ProductoDao;
import com.example.producto.entity.ProductoEntity;
import com.example.producto.entity.model.Categoria;
import com.example.producto.entity.model.Proveedor;
import com.example.producto.service.ProductoService;
import com.example.producto.util.request.RequestProducto;
import com.example.producto.util.response.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class ProductoServiceImpl  implements ProductoService {

    private final ProveedorClientRest proveedorClientRest;
    private final CategoriaClientRest categoriaClientRest;
    private final ProductoDao productoDao ;

    public ProductoServiceImpl(ProveedorClientRest proveedorClientRest, CategoriaClientRest categoriaClientRest, ProductoDao productoDao) {
        this.proveedorClientRest = proveedorClientRest;
        this.categoriaClientRest = categoriaClientRest;
        this.productoDao = productoDao;
    }


    @Override
    public ResponseBase clientByIdProveedor(Long id) {

        ResponseBase responseBase = proveedorClientRest.byOneProveedor(id);
        Optional <Proveedor>proveedors =  responseBase.getData();
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(proveedors));

    }

    @Override
    public ResponseBase clientByIdCategoria(Long id) {
        ResponseBase responseBase = categoriaClientRest.findOneCategoria(id);
        Optional<Categoria> categoria = responseBase.getData();
        return  new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(categoria));
    }

    @Override
    public ResponseBase allProducto() {
        List<ProductoEntity>productoEntities =productoDao.findAll();
        if (productoEntities.isEmpty())
        {
            return new ResponseBase(Constants.ERROR_CODE,Constants.NOT_FOUND_MESSAGE,Optional.of(List.of()));
        }
        else {
            int amount = productoEntities.size();
            return new ResponseBase(Constants.SUCCESS_CODE,Constants.SUCCESS_MESSAGE,Optional.of(List.of(productoEntities, Map.of("amount",amount))));
        }

    }

    @Override
    public ResponseBase addProducto(RequestProducto requestProducto) {
        ResponseBase responseCategoria = categoriaClientRest.findOneCategoria(requestProducto.getId_categoria());
        ResponseBase responseProveedor = proveedorClientRest.byOneProveedor(requestProducto.getId_proveedor());

        if (responseCategoria.getCode() == 404) {
            return new ResponseBase(Constants.ERROR_CODE, "La categoría con id " + requestProducto.getId_categoria() + " no existe", Optional.empty());
        }

        if (responseProveedor.getCode() == 404) {
            return new ResponseBase(Constants.ERROR_CODE, "El proveedor con id " + requestProducto.getId_proveedor() + " no existe", Optional.empty());
        }

        ProductoEntity producto = getProducto(new ProductoEntity(), requestProducto, false);
        productoDao.save(producto);
        return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.of(producto));

    }

    @Override
    public ResponseBase searchNameProducto(String name) {
        ResponseBase allProductosResponse = allProducto();
        List<?> listado = (List<?>) allProductosResponse.getData().get();
        List<ProductoEntity> productos = (List<ProductoEntity>) listado.get(0);

        Optional<ProductoEntity> opProducto = productos.stream()
                .filter(producto -> producto.getNombre().equals(name))
                .findFirst();

        if (opProducto.isPresent()) {
            // Se encontró un producto con el nombre proporcionado
            return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, opProducto);
        } else {
            // No se encontró ningún producto con el nombre proporcionado
            return new ResponseBase(Constants.ERROR_CODE, "No se encontró ningún producto con el nombre: " + name, Optional.empty());
        }

    }

    @Override
    public ResponseBase findOneProducto(Long id) {
        Optional<ProductoEntity> producto = productoDao.findById(id);
        if (producto.isPresent()) {
            ResponseBase responseCategoria;
            try {
                responseCategoria = categoriaClientRest.findOneCategoria(producto.get().getId_categoria());
            } catch (Exception e) {

                log.error("Error al comunicarse con el servicio de categoría", e);
                responseCategoria = new ResponseBase(500, "Servicio de categoría no disponible", Optional.empty());
            }

            ResponseBase responseProveedor;
            try {
                responseProveedor = proveedorClientRest.byOneProveedor(producto.get().getId_proveedor());
            } catch (Exception e) {

                log.error("Error al comunicarse con el servicio de proveedor", e);
                responseProveedor = new ResponseBase(500, "Servicio de proveedor no disponible", Optional.empty());
            }

            List<Object> encontrado = new ArrayList<>();
            if (responseCategoria != null && responseCategoria.getData().isPresent()) {
                encontrado.add(responseCategoria.getData().get());
            } else {
                encontrado.add(null);
            }

            if (responseProveedor != null && responseProveedor.getData().isPresent()) {
                encontrado.add(responseProveedor.getData().get());
            } else {
                encontrado.add(null);
            }

            return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.of(List.of(producto.get(), encontrado)));
        }

        return new ResponseBase(Constants.NOT_FOUND_CODE, Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));
    }


    public Timestamp getTimesTamp() {
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }


    public ProductoEntity getProducto (ProductoEntity producto , RequestProducto requestProducto,boolean isUpdate)
    {
        producto.setNombre(requestProducto.getNombre());
        producto.setDescripcion(requestProducto.getDescripcion());
        producto.setEstado(1);
        producto.setStock(requestProducto.getStock());
        producto.setPrecio_compra(requestProducto.getPrecio_compra());
        producto.setPrecio_venta(requestProducto.getPrecio_venta());
        producto.setId_proveedor(requestProducto.getId_proveedor());
        producto.setId_categoria(requestProducto.getId_categoria());
        if (isUpdate)
        {
            producto.setDate_modify(getTimesTamp());
        }
        else {
            producto.setDate_create(getTimesTamp());
        }
        return producto;

    }

}
