package com.example.lab10.controller;

import com.example.lab10.model.Product;
import com.example.lab10.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ProductController — Reactive REST Controller
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    // ── Constructor Injection (DIP — SOLID) ─────────────
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่างที่ทำเสร็จแล้ว
    // ══════════════════════════════════════════════════════

    /**
     * GET /products/{id}
     * คืน Mono<Product> — ค้นหา Product 1 รายการ
     */
    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.getById(id);
    }

    // ══════════════════════════════════════════════════════
    // ❌ Endpoints ที่เติมเพิ่มจนครบสมบูรณ์
    // ══════════════════════════════════════════════════════

    /**
     * GET /products
     * คืน Flux<Product> ทุกรายการ
     */
    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    /**
     * POST /products
     * รับ Product จาก request body แล้วบันทึก
     */
    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    /**
     * DELETE /products/{id}
     * ลบ Product และคืน Mono<Void>
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    /**
     * GET /products/category/{category}
     * คืน Flux<Product> ที่กรองตาม category
     */
    @GetMapping("/category/{category}")
    public Flux<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    /**
     * GET /products/{id}/price
     * คืน Mono<Double> ราคาหลังส่วนลด
     */
    @GetMapping("/{id}/price")
    public Mono<Double> getDiscountedPrice(@PathVariable String id) {
        return service.getDiscountedPrice(id);
    }
}