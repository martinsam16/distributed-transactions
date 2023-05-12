package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.martinsam.catalogproducts.application.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API de productos")
public class ProductPatchRest {

    private final ProductService productService;

    @PatchMapping(value = "/{id}/increase/{quantity}")
    public ResponseEntity<Void> increaseStock(
            @PathVariable
            @Valid
            @NotBlank(message = "El campo 'id' es obligatorio")
            String id,
            @PathVariable
            @Valid
            @NotNull(message = "El campo 'quantity' es obligatorio")
            @PositiveOrZero(message = "La cantidad no puede ser negativa")
            Integer quantity
    ) {
        productService.increaseStock(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/decrease/{quantity}")
    public ResponseEntity<Void> decreaseStock(
            @PathVariable
            @Valid
            @NotBlank(message = "El campo 'id' es obligatorio")
            String id,
            @PathVariable
            @Valid
            @NotNull(message = "El campo 'quantity' es obligatorio")
            @PositiveOrZero(message = "La cantidad no puede ser negativa")
            Integer quantity
    ) {
        productService.decreaseStock(id, quantity);
        return ResponseEntity.ok().build();
    }
}
