package pe.martinsam.catalogproducts.infraestructure.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.martinsam.catalogproducts.application.ProductService;
import pe.martinsam.catalogproducts.domain.model.product.dto.GetProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API de productos")
public class ProductGetRest {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public ResponseEntity<List<GetProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por id")
    public ResponseEntity<GetProductDto> getProductById(
            @PathVariable
            String id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/count")
    @Operation(summary = "Cantidad total de productos")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(productService.countProducts());
    }

    @GetMapping("/count/{sellerId}")
    @Operation(summary = "Cantidad total de productos por vendedor")
    public ResponseEntity<Long> countBySellerId(
            @PathVariable
            String sellerId
    ) {
        return ResponseEntity.ok(productService.countProductsBySellerId(sellerId));
    }

}
