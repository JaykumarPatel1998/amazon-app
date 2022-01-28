package io.amazon.ecommerce.Repository;

import io.amazon.ecommerce.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
