package com.example.menu.service;

import com.example.menu.domain.entity.Menu;
import com.example.menu.domain.request.MenuRequest;
import com.example.menu.domain.response.MenuResponse;
import com.example.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public void menuSave(MenuRequest menuRequest) {
        menuRepository.save(menuRequest.toEntity());
    }

    public Page<MenuResponse> getByStoreId(Long storeId, PageRequest pageRequest) {
        return menuRepository.findByStore(
                storeId,
                pageRequest);
    }

    public List<MenuResponse> getByIds(List<Long> ids) {
        List<Menu> menus = menuRepository.findByIdIn(ids);
        return menus.stream().map(MenuResponse::of).toList();
    }

}
