package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@EnableCaching
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
//@CacheConfig(cacheNames = "users-list")  => butun cache antoasyonlarina value = "users-list"
//boyle girecegini sadece @CacheConfig girmen daha mantikli. cunku tum classi kapsar.
public class UserController {

    private final UserServiceImpl service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{identity}")
    public ResponseEntity<?> getById(@PathVariable Long identity) {
        return service.getById(identity);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/update/{identity}")
    public ResponseEntity<?> update(@RequestBody UserRequestDTO dto, @PathVariable Long identity) {
        return service.update(dto, identity);
    }

    @DeleteMapping("/delete/{identity}")
    public ResponseEntity<?> delete(@PathVariable Long identity) {
        return service.delete(identity);
    }

    //All Cache Methods And Annotations

    private final CacheManager cacheManager;

    @GetMapping({"/getCache", "/getCache/{cacheName}"})
    public ResponseEntity<?> getCache(@PathVariable(required = false) String cacheName) {
        if (cacheName == null) return new ResponseEntity<>(cacheManager, HttpStatus.OK);
        else return new ResponseEntity<>(cacheManager.getCache(cacheName), HttpStatus.OK);
    }

    @GetMapping("/cacheGetById/{identity}")
    @Cacheable(value = "users-list", key = "'users-list'+#identity")
    public ResponseEntity<?> cacheGetById(@PathVariable Long identity) {
        try {
            Thread.sleep(3000);
            return getById(identity);
        } catch (Exception e) {
            return new ResponseEntity<>("Have an error in getById() function!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cacheDeleteById/{identity}")
    @CacheEvict(value = "users-list", key = "'users-list'+#identity")
    public ResponseEntity<?> cacheDeleteById(@PathVariable Long identity) {
        return new ResponseEntity<>(identity + " is deleted in cache list!", HttpStatus.OK);
    }

    @GetMapping("/cacheUpdateById/{identity}")
    @CachePut(value = "users-list", key = "'users-list'+#identity")
    public ResponseEntity<?> cacheUpdateById(@PathVariable Long identity) {
        return cacheGetById(identity);
    }

    //@Caching anotasyonu icinde @Cacheable, @CacheEvict, @CachePut anotasyonlarinin her birinin
    //ayri ayri array i var icinde, @Caching antoasyonun amaci bu 3 antoasyonu ayni anda kulanabilmek.
    //Assagidaki @Caching veririyi cachelere kaydetmeden cagirmani saglar. Kaydedilmis veri varsada siler.
    @GetMapping("cacheDeleteGetById/{identity}")
    @Caching(evict = {@CacheEvict(value = "users-list", key = "'users-list'+#identity")},
            cacheable = {@Cacheable(value = "users-list", key = "'users-list'+#identity")})
    public ResponseEntity<?> cacheDeleteGetById(@PathVariable Long identity) {
        return cacheGetById(identity);
    }

    //Eğer uygulamadan önbellek özelliğini kaldırmak istersek, tüm anotasyonları silmek yerine
    //application.properties dosyasına spring.cache.type=none satırı eklenir.
}