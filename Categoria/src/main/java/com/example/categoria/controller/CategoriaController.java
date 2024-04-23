package com.example.categoria.controller;

import com.example.categoria.constants.Constants;
import com.example.categoria.service.CategoriaService;
import com.example.categoria.util.request.RequestCategoria;
import com.example.categoria.util.response.ResponseBase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping
    public ResponseBase findAllCategoria()
    {
        ResponseBase responseBase = categoriaService.findAllCategoria();
         return  responseBase;
    }


    @GetMapping("/redis/{id}")
    public ResponseBase findOneCategoriaRedis(@PathVariable Long id) {

            ResponseBase responseBase = categoriaService.findOneCategoriaRedis(id);
            return  responseBase;
    }

    @GetMapping("/{id}")
    public ResponseBase findOneCategoria(@PathVariable Long id) {

        ResponseBase responseBase = categoriaService.findOneCategoria(id);
        return  responseBase;
    }



    @PostMapping
    public  ResponseBase createdCategoria(@Valid @RequestBody RequestCategoria requestCategoria, BindingResult result)
    {
        if (result.hasErrors()) {

            return new ResponseBase(Constants.ERROR_CODE, Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }

        ResponseBase response = categoriaService.createCategoria(requestCategoria);
        if (response.getData().isPresent()) {
            return response;
        } else {
            return new ResponseBase(Constants.ERROR_CODE, "Error al crear la categoría", Optional.empty());
        }
    }

    @PutMapping("/{id}")
    public ResponseBase updateCategoria(@Valid @RequestBody RequestCategoria requestCategoria, BindingResult result,@PathVariable Long id)
    {
        if (result.hasErrors()) {

            return new ResponseBase(Constants.ERROR_CODE, Constants.ERROR_MESSAGE, Optional.of(validar(result).getBody()));
        }
        ResponseBase response = categoriaService.updateCategoria(id,requestCategoria);
        return  response;



    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String,String>errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField()+" "+err.getDefaultMessage());});
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
