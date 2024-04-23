package com.example.producto.clients;

import com.example.producto.util.response.ResponseBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Categoria",url = "msvc-categoria:8001/api/categoria")
public interface CategoriaClientRest {


    @GetMapping("/{id}")
    ResponseBase findOneCategoria(@PathVariable Long id);


}
