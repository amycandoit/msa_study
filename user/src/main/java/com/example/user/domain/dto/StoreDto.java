package com.example.user.domain.dto;

import com.example.user.domain.entity.Store;
import lombok.Getter;

@Getter
public class StoreDto {
    private Long id;
    private String placeId;
    private String name;
    private String address;
    private String phone;
    private String foodType;
    private String storeImgUrl;
    private String coordinateX;
    private String coordinateY;
    private String myReview;

    public StoreDto(Long id, String placeId, String name, String address, String phone, String foodType, String storeImgUrl, String coordinateX, String coordinateY, String myReview) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.foodType = foodType;
        this.storeImgUrl = storeImgUrl;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.myReview = myReview;
    }

    public StoreDto(Store store) {
        this.id = store.getId();
        this.placeId = store.getPlaceId();
        this.name = store.getName();
        this.address = store.getAddress();
        this.phone = store.getPhone();
        this.foodType = store.getFoodType();
        this.storeImgUrl = store.getStoreImgUrl();
        this.coordinateX = store.getCoordinateX();
        this.coordinateY = store.getCoordinateY();
        this.myReview = store.getMyReview();
    }
}
