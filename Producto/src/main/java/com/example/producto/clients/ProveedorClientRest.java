package com.example.producto.clients;


import com.example.producto.util.response.ResponseBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Proveedor",url = "msvc-proveedor:8002/api/proveedor")
public interface ProveedorClientRest {

    @GetMapping
    ResponseBase allProveedor();

    @GetMapping("/{id}")
    ResponseBase byOneProveedor(@PathVariable Long id);

}
