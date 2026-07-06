package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // POST /api/menu (create a new menu item)
    @PostMapping
    public ResponseEntity<Response<MenuDto>> create(
            @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    // GET /api/menu using categoryId=&search=&page=&size=&sort=
    @GetMapping
    public ResponseEntity<Response<Page<MenuDto>>> getAll(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        // Parse sort parameter (price,asc or price,desc)
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        Sort.Direction direction = sortParts.length > 1 &&
                sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(direction, sortField));

        return ResponseEntity.ok(
                menuService.getAllMenus(categoryId, search, pageable));
    }

    // GET /api/menu/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    // PUT /api/menu/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.updateMenu(id, dto));
    }

    // DELETE /api/menu/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(
            @PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}