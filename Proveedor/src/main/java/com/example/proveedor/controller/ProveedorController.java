package com.example.proveedor.controller;

import com.example.proveedor.constants.Constants;
import com.example.proveedor.service.ProveedorService;
import com.example.proveedor.util.request.RequestProveedor;
import com.example.proveedor.util.response.ResponseBase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;


    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseBase allProveedor ( )
    {
        ResponseBase responseBase = proveedorService.findAllProveedor();
        return  responseBase;

    }

    @GetMapping("/{id}")
    public  ResponseBase  byOneProveedor(@PathVariable Long id)
    {
        ResponseBase responseBase = proveedorService.findOneProveedor(id);
        return responseBase;
    }


    @PostMapping
    public ResponseBase addProveedor(@Valid @RequestBody RequestProveedor requestProveedor, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseBase(Constants.ERROR_CODE, Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }
        ResponseBase responseBase = proveedorService.createProveedor(requestProveedor);
        return responseBase;

    }

    @PutMapping("/{id}")
    public ResponseBase updateProveedor(@Valid @RequestBody RequestProveedor requestProveedor,BindingResult result,@PathVariable Long id)
    {
        if (result.hasErrors())
        {
            return new ResponseBase(Constants.ERROR_CODE, Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }
        ResponseBase responseBase = proveedorService.updateProveedor(id,requestProveedor);
        return responseBase;
    }

    @DeleteMapping("/{id}")
    public ResponseBase deleleteProveedor(@PathVariable Long id) {
        ResponseBase responseBase= proveedorService.deleteProveedor(id);
        return  responseBase;
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String,String>errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField()+" "+err.getDefaultMessage());});
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
