package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {
        Category category = findCategoryOrThrow(dto.getCategoryId());
        Menu saved = menuRepository.save(mapToEntity(dto, category));
        return Response.success("Menu created successfully", mapToDto(saved));
    }

    @Override
    public Response<Page<MenuDto>> getAllMenus(Long categoryId, String search, Pageable pageable) {
        Page<MenuDto> page = menuRepository
                .findByFilters(categoryId, search, pageable)
                .map(this::mapToDto);
        return Response.success("Menus retrieved successfully", page);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {
        Menu menu = findMenuOrThrow(id);
        return Response.success("Menu retrieved successfully", mapToDto(menu));
    }

    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {
        Menu menu = findMenuOrThrow(id);
        Category category = findCategoryOrThrow(dto.getCategoryId());

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu updated = menuRepository.save(menu);
        return Response.success("Menu has been updated successfully", mapToDto(updated));
    }

    @Override
    public Response<Void> deleteMenu(Long id) {
        findMenuOrThrow(id);
        menuRepository.deleteById(id);
        return Response.success("Menu has been deleted successfully", null);
    }

    //  Private helpers

    private Category findCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + categoryId));
    }

    private Menu findMenuOrThrow(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));
    }

    private MenuDto mapToDto(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .categoryId(menu.getCategory().getId())
                .categoryName(menu.getCategory().getName())
                .build();
    }

    private Menu mapToEntity(MenuDto dto, Category category) {
        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}
