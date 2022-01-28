package io.amazon.ecommerce.Controller;

import io.amazon.ecommerce.Entity.Product;
import io.amazon.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct (@RequestBody Product product) {
        productRepository.save(product);
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        response.put("serviceResponse", "product created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts () {
        Iterable<Product> products = productRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("productList", products);
        response.put("serviceResponse", "products fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct (@PathVariable(value = "id") Integer productId) {
        productRepository.deleteById(productId);
        Map<String, Object> response = new HashMap<>();
        response.put("serviceResponse", "product deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateProduct (@RequestBody Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()) {
            throw new ResourceAccessException("resource to be updated does not exist in database!!");
        }
        else {
            productRepository.save(product);
            Map<String, Object> response = new HashMap<>();
            response.put("product", product);
            response.put("serviceResponse", "product updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
