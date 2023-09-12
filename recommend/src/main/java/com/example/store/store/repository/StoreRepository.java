package com.example.store.store.repository;


import com.example.store.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByPlaceId(String id);

    Store findAllByPlaceId(String id);


    Page<Store> findAll(Pageable request);

    @Query("select s from Store s where s.address like %:keyword%")
    Page<Store> findAllByAddressContaining(Pageable request, String keyword);
}
