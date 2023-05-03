package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    @ApiOperation("Crear un producto")
    public ResponseEntity<Product> addProduct(@Validated @RequestBody CreateProductDto productDto){
        return ResponseEntity.ok(productService.addProduct(productDto));
    }



}
