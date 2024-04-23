package com.example.categoria.service.impl;

import com.example.categoria.constants.Constants;
import com.example.categoria.dao.CategoriaDao;
import com.example.categoria.entity.CategoriaEntity;
import com.example.categoria.redis.RedisService;
import com.example.categoria.redis.RedisUtil;
import com.example.categoria.service.CategoriaService;
import com.example.categoria.util.request.RequestCategoria;
import com.example.categoria.util.response.ResponseBase;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDao categoriaDao;
    private final RedisService redisService;

    private final RedisConnectionFactory redisConnectionFactory;

    public CategoriaServiceImpl(CategoriaDao categoriaDao, RedisService redisService, RedisConnectionFactory redisConnectionFactory) {
        this.categoriaDao = categoriaDao;
        this.redisService = redisService;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseBase findAllCategoria() {

        List<CategoriaEntity> categorias = categoriaDao.findAll();

        if (!categorias.isEmpty()) {
            return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.of(categorias));
        } else {
            return new ResponseBase(Constants.NOT_FOUND_CODE, Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseBase findOneCategoriaRedis(Long id) {
        String redisCache = redisService.getValueByKey("NC:SISTEMA:CATEGORIA" + id);
        if (redisCache != null ) {
            CategoriaEntity categoriaEntity = RedisUtil.convertFromJson(redisCache, CategoriaEntity.class);
            return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.ofNullable(categoriaEntity));
        } else {
            Optional<CategoriaEntity> oneCategoria = categoriaDao.findById(id);
            if (oneCategoria.isPresent()) {
                CategoriaEntity categoriaEntity = oneCategoria.get();
                String redisData = RedisUtil.converToJsonEntity(categoriaEntity);
                redisService.saveKeyValue("NC:SISTEMA:CATEGORIA" + id, redisData, 200);
                return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.ofNullable(categoriaEntity));
            } else {
                // Manejar el caso en el que no se encontró la categoría
                return new ResponseBase(Constants.NOT_FOUND_CODE, Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));
            }
        }

    }

    @Override
    public ResponseBase findOneCategoria(Long id) {
        Optional<CategoriaEntity> oneCategoria = categoriaDao.findById(id);
            if (oneCategoria.isPresent())
            {
                return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,oneCategoria);

            }
            else {
                return new ResponseBase(Constants.NOT_FOUND_CODE, Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));

            }

    }

    @Transactional
    @Override
    public ResponseBase createCategoria(RequestCategoria requestCategoria) {

        CategoriaEntity categoria = getCategoria(new CategoriaEntity(), requestCategoria,false);
        categoriaDao.save(categoria);
        return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.of(categoria));

    }

    @Transactional
    @Override
    public ResponseBase updateCategoria(Long id, RequestCategoria requestCategoria) {
     Optional<CategoriaEntity> catEncotrada = categoriaDao.findById(id);
     if (catEncotrada.isPresent())
     {
      CategoriaEntity categoria =  catEncotrada.get();
      categoria= getCategoria(categoria,requestCategoria,true);
      categoriaDao.save(categoria);
      return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, Optional.of(categoria));
     }
        return new ResponseBase(Constants.INVALID_PARAMETERS_CODE, Constants.NOT_FOUND_MESSAGE, Optional.of(List.of()));

    }

    public Timestamp getTimesTamp() {
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    public CategoriaEntity getCategoria(CategoriaEntity categoria, RequestCategoria requestCategoria,boolean isUpdate) {
        categoria.setNombre(requestCategoria.getNombre());
        categoria.setDescripcion(requestCategoria.getDescripcion());
        categoria.setEstado(1);
        if (isUpdate){
            categoria.setDate_modify(getTimesTamp());

        }
        else{
            categoria.setDate_create(getTimesTamp());
        }

        return categoria;
    }

}
