package com.example.cliente.controller;

import com.example.cliente.constants.Constants;
import com.example.cliente.service.ClienteService;
import com.example.cliente.util.request.RequestCliente;
import com.example.cliente.util.response.ResponseBase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseBase allClientes()
    {
        ResponseBase responseBase = clienteService.allClientes();
        return  responseBase;

    }


    @GetMapping("/{id}")
    public ResponseBase searchCliente(@PathVariable Long id)
    {
        ResponseBase responseBase = clienteService.seachCliente(id);
        return  responseBase;

    }

    @PostMapping
    public  ResponseBase addCliente(@Valid @RequestBody RequestCliente requestCliente , BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseBase(Constants.ERROR_CODE,Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }
        ResponseBase responseBase = clienteService.addCliente(requestCliente);
        return  responseBase;
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String,String>errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField()+" "+err.getDefaultMessage());});
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
