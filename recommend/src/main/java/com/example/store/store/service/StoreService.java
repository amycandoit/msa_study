package com.example.store.store.service;

import com.example.store.domain.entity.Store;
import com.example.store.domain.response.StoreResponse;
import com.example.store.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void saveSelectedPlaces(String data, String reviews) {

        JSONParser parser = new JSONParser();
        try {
            JSONArray selectData = (JSONArray) parser.parse(data);

            for (Object obj : selectData) {
                JSONObject dataJson = (JSONObject) obj;

                Store store = Store.builder()
                        .placeId((String) dataJson.get("id"))
                        .name((String) dataJson.get("place_name"))
                        .address((String) dataJson.get("address_name"))
                        .phone((String) dataJson.get("phone"))
                        .foodType((String) dataJson.get("category_name"))
                        .storeImgUrl((String) dataJson.get("place_url"))
                        .coordinateX((String) dataJson.get("x"))
                        .coordinateY((String) dataJson.get("y"))
                        .myReview(reviews) // Set the reviews value
                        .build();

                if (!storeRepository.existsByPlaceId(store.getPlaceId())) {
                    storeRepository.save(store);
                } else {
                    throw new RuntimeException("STORE WITH PLACE ID ALREADY EXISTS.");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("FAILED TO PARSE DATA.");
        }
    }


    public Page<StoreResponse> findAll(PageRequest request) {
        Page<Store> all = storeRepository.findAll(request);
        return all.map(StoreResponse::new);
    }

    public Page<StoreResponse> findByAddressContains(PageRequest request, String keyword) {
        Page<Store> all = storeRepository.findAllByAddressContaining(request, keyword);
        return all.map(StoreResponse::new);
    }

    public StoreResponse findByPlaceId(String placeId) {
        Store store = storeRepository.findAllByPlaceId(placeId);
        return new StoreResponse(store);
    }

}
