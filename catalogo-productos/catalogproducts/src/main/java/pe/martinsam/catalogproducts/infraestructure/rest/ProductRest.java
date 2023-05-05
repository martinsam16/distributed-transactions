package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.martinsam.catalogproducts.application.ProductService;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.dto.CreateProductDto;
import pe.martinsam.catalogproducts.domain.model.product.dto.GetProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Api(tags = "Productos")
public class ProductRest {

    private final ProductService productService;

    @GetMapping
    @ApiOperation("Obtener todos los productos")
    public ResponseEntity<List<GetProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/count")
    @ApiOperation("Cantidad total de productos")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(productService.countProducts());
    }

    @GetMapping("/count/{sellerId}")
    @ApiOperation("Cantidad total de productos por vendedor")
    public ResponseEntity<Long> countBySellerId(
            @PathVariable
            String sellerId
    ) {
        return ResponseEntity.ok(productService.countProductsBySellerId(sellerId));
    }

    @PostMapping
    @ApiOperation("Crear un producto")
    public ResponseEntity<Product> addProduct(
            @RequestBody
            @Valid
            CreateProductDto productDto,
            @NotNull
            WebRequest request
    ) {
        final Product product = productService.addProduct(productDto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path(request.getContextPath())
                                .build()
                                .toUri())
                .body(product);
    }

}
