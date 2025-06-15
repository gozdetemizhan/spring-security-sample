package com.example.web;

import com.example.model.Product;
import com.example.repo.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository repo;
  public ProductController(ProductRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('PRODUCT_READ')")
  public List<Product> list() {
    return repo.findAll();
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
  public Product update(
      @PathVariable Long id,
      @RequestBody Product incoming
  ) {
    Product p = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + id));
    p.setName(incoming.getName());
    p.setDescription(incoming.getDescription());
    return repo.save(p);
  }
}
