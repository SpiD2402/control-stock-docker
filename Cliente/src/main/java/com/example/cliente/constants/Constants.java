package com.example.cliente.constants;

public class Constants {
    // Mensajes Genéricos
    public static final String SUCCESS_MESSAGE = "Operación exitosa";
    public static final String ERROR_MESSAGE = "Ha ocurrido un error";
    public static final String NOT_FOUND_MESSAGE = "Recurso no encontrado";
    public static final String UNAUTHORIZED_MESSAGE = "Acceso no autorizado";
    public static final String FORBIDDEN_MESSAGE = "Acción prohibida";

    // Códigos de Error
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;
    public static final int NOT_FOUND_CODE = 404;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;

    // Mensajes Específicos
    public static final String INVALID_REQUEST_MESSAGE = "Solicitud inválida";
    public static final String INVALID_PARAMETERS_MESSAGE = "Parámetros de solicitud inválidos";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Error interno del servidor";
    public static final String DUPLICATE_RESOURCE_MESSAGE = "Recurso duplicado";

    // Códigos de Error Específicos
    public static final int INVALID_REQUEST_CODE = 400;
    public static final int INVALID_PARAMETERS_CODE = 422;
    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final int DUPLICATE_RESOURCE_CODE = 409;

    // Otros Valores Constantes
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_ERROR_CODE = 500;
    public static final String DEFAULT_ERROR_MESSAGE = "Ha ocurrido un error";
}
