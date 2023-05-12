package pe.martinsam.catalogproducts.common.infraestructure.rest.handler.dto;

public record ErrorRestHandlerDto(
        String timestamp,
        Integer status,
        String error,
        String message,
        String path) {
}
