package com.example.producto.controller;

import com.example.producto.constants.Constants;
import com.example.producto.service.MovimientoInventarioService;
import com.example.producto.util.request.RequestMovimientoInventario;
import com.example.producto.util.response.ResponseBase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    public MovimientoInventarioController(MovimientoInventarioService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseBase addMovimientoInventaro(@Valid @RequestBody RequestMovimientoInventario inventario , BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseBase(Constants.ERROR_CODE,Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }

        ResponseBase responseBase = movimientoService.addMovimientoInventario(inventario);

        return  responseBase;

    }



    @GetMapping
    public ResponseBase allMovimientoInventario()
    {
        ResponseBase responseBase = movimientoService.allMovimientoInventario();
        return  responseBase;
    }



    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String,String>errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField()+" "+err.getDefaultMessage());});
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }


}
