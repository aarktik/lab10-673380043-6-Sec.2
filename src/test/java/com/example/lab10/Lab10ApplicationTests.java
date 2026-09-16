package com.example.lab10;

import com.example.lab10.model.Product;
import com.example.lab10.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

/**
 * Lab10ApplicationTests — ทดสอบ Reactive code
 */
@SpringBootTest
class Lab10ApplicationTests {

    @Autowired
    private ProductRepository repository;

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่าง test
    // ══════════════════════════════════════════════════════

    @Test
    void contextLoads() {
        // Spring Application Context โหลดสำเร็จ
    }

    @Test
    void testFindById_found() {
        StepVerifier.create(repository.findById("1"))
                .expectNextMatches(p -> p.getName().contains("iPhone"))
                .verifyComplete();
    }

    @Test
    void testFindById_notFound() {
        StepVerifier.create(repository.findById("999"))
                .verifyComplete();
    }

    // ══════════════════════════════════════════════════════
    // ❌ Test cases ที่เติมเพิ่มจนครบสมบูรณ์
    // ══════════════════════════════════════════════════════

    @Test
    void testFindAll() {
        StepVerifier.create(repository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testSave() {
        Product newProduct = new Product("4", "iPad Air", "Electronics", "Apple", 15, 23900.0, "MEMBER");

        StepVerifier.create(repository.save(newProduct))
                .expectNextMatches(p -> p.getId().equals("4") && p.getName().equals("iPad Air"))
                .verifyComplete();
    }

    @Test
    void testFindByCategory() {
        StepVerifier.create(repository.findByCategory("Electronics"))
                .expectNextCount(3)
                .verifyComplete();
    }
}