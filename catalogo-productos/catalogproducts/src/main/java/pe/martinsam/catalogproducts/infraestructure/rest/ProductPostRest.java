package pe.martinsam.catalogproducts.infraestructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.martinsam.catalogproducts.application.ProductService;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.dto.CreateProductDto;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API de productos")
public class ProductPostRest {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Crear un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Producto creado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"id\": \"6454deb19ff38211c5c207aa\",\n" +
                                    "    \"sellerId\": \"APPLE\",\n" +
                                    "    \"name\": \"Apple iPhone 14 Pro Max\",\n" +
                                    "    \"brand\": \"Apple\",\n" +
                                    "    \"model\": \"iPhone 14 Pro Max\",\n" +
                                    "    \"description\": \"El iPhone 14 Pro Max te permite captar detalles increíbles gracias a su cámara gran angular de 48 MP. Además, trae la Dynamic Island y una pantalla siempre activa, para que puedas interactuar con tu iPhone de una forma completamente nueva. Y viene con Detección de Choques(1), una funcionalidad de seguridad que pide ayuda cuando no estés en condiciones de hacerlo.\",\n" +
                                    "    \"imageUrl\": [\n" +
                                    "        \"https://http2.mlstatic.com/D_NQ_NP_2X_605126-MLM51559383638_092022-F.webp\",\n" +
                                    "        \"https://http2.mlstatic.com/D_NQ_NP_2X_807334-MLM51559383644_092022-F.webp\"\n" +
                                    "    ],\n" +
                                    "    \"additionalProperties\": {\n" +
                                    "        \"Tamaño de la pantalla\": \"6.7''\",\n" +
                                    "        \"Con eSIM\": true,\n" +
                                    "        \"Memoria interna\": \"256 GB\",\n" +
                                    "        \"Memoria RAM\": \"6 GB\"\n" +
                                    "    },\n" +
                                    "    \"currencySymbol\": \"PEN\",\n" +
                                    "    \"price\": 6399,\n" +
                                    "    \"stock\": 12,\n" +
                                    "    \"categories\": [\n" +
                                    "        {\n" +
                                    "            \"id\": \"APPLE_ID\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": \"MOBILE_ID\"\n" +
                                    "        }\n" +
                                    "    ],\n" +
                                    "    \"tags\": [\n" +
                                    "        \"apple\",\n" +
                                    "        \"iphone\"\n" +
                                    "    ],\n" +
                                    "    \"isFreeShipping\": true\n" +
                                    "}"))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"timestamp\": \"2023-05-05T07:14:19.900774\",\n" +
                                    "    \"status\": 400,\n" +
                                    "    \"error\": \"Validation Error\",\n" +
                                    "    \"message\": \"{sellerId=El campo 'sellerId' es obligatorio}\",\n" +
                                    "    \"path\": \"/api/v1/products\"\n" +
                                    "}"))
            ),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"timestamp\": \"2023-05-05T07:13:58.538925\",\n" +
                                    "    \"status\": 409,\n" +
                                    "    \"error\": \"Product already exists\",\n" +
                                    "    \"message\": \"Product already exists\",\n" +
                                    "    \"path\": \"/api/v1/products\"\n" +
                                    "}"))
            )
    })
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
