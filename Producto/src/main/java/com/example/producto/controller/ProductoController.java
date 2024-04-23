package com.example.producto.controller;

import com.example.producto.constants.Constants;
import com.example.producto.entity.model.Proveedor;
import com.example.producto.service.ProductoService;
import com.example.producto.util.request.RequestProducto;
import com.example.producto.util.response.ResponseBase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public  ResponseBase allProducto()
    {
        ResponseBase responseBase = productoService.allProducto();
        return  responseBase;
    }

    @GetMapping("/{id}")
    public  ResponseBase getIdProducto(@PathVariable Long id)
    {
        ResponseBase responseBase =  productoService.findOneProducto(id);
        return  responseBase;
    }

    @PostMapping
    public  ResponseBase addProducto(@Valid @RequestBody RequestProducto requestProducto,BindingResult result)
    {
        if (result.hasErrors())

        {
            return  new ResponseBase(Constants.ERROR_CODE,Constants.ERROR_MESSAGE,Optional.of(validar(result).getBody()));
        }

        ResponseBase responseBase = productoService.addProducto(requestProducto);
        return  responseBase;

    }





    @GetMapping("/proveedor/{id}")
       public ResponseBase clientByIdProveedor(@PathVariable Long id)
    {
        ResponseBase responseBase = productoService.clientByIdProveedor(id);
        return responseBase;
    }

    @GetMapping("/categoria/{id}")
    public ResponseBase clientByIdCategoria(@PathVariable Long id)
    {
        ResponseBase responseBase = productoService.clientByIdCategoria(id);
        return responseBase;
    }

    @GetMapping("/nombre/{name}")
    public ResponseBase searchNameProducto(@PathVariable String name)
    {
        ResponseBase responseBase = productoService.searchNameProducto(name);
        return  responseBase;
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String,String>errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField()+" "+err.getDefaultMessage());});
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
