package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public List<GetProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/count")
    @ApiOperation("Cantidad total de productos")
    public Long count() {
        return productService.countProducts();
    }

    @GetMapping("/count/{sellerId}")
    @ApiOperation("Cantidad total de productos por vendedor")
    public Long countBySellerId(@PathVariable String sellerId) {
        return productService.countProductsBySellerId(sellerId);
    }

    @PostMapping
    @ApiOperation("Crear un producto")
    public ResponseEntity<Product> addProduct(@Validated @RequestBody CreateProductDto productDto){
        return ResponseEntity.ok(productService.addProduct(productDto));
    }



}
