package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.martinsam.catalogproducts.application.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API de productos")
public class ProductDeleteRest {

    private final ProductService productService;

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Cambia el estado de un producto a DELETED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "timestamp": "2023-05-05T07:53:09.038118",
                                      "status": 409,
                                      "error": "Product not found",
                                      "message": "Product not found",
                                      "path": "/api/v1/products/6454deb19ff38211c5c107aa"
                                    }"""))
            )
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
