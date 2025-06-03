package com.technova.shopverse.controller;

import com.technova.shopverse.dto.CategoryDTO;

import com.technova.shopverse.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias de productos")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "Obtener todas las categorias",
            description = "Devuelve una lista con todas las categorias disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada correctamente")
    @ApiResponse(responseCode = "204", description = "No hay categorias disponibles")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Obtener una categoria por ID",
            description = "Devuelve la categoria correspondiente al ID proporcionado"
    )
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(
            @Parameter(description = "ID de la categoria a buscar", required = true)
            @PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Crear una nueva categoria",
            description = "Crea una nueva categoria"
    )
    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos invalidos o malformados")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @Parameter(description = "Datos de la nueva categoria", required = true)
            @Valid @RequestBody CategoryDTO categoryDto) {
        try {
            CategoryDTO createdCategory = categoryService.createCategory(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Actualizar una categoria existente",
            description = "Actualiza los datos de una categoria por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Categoria actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(
            @Parameter(description = "ID de la categoria a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la categoria", required = true)
            @Valid @RequestBody CategoryDTO categoryDto) {
        try {
            CategoryDTO updated = categoryService.updateCategory(id, categoryDto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Eliminar una categoria",
            description = "Elimina una categoria por su ID"
    )
    @ApiResponse(responseCode = "204", description = "Categoria eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "ID de la categoria a eliminar", required = true)
            @PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Obtener detalles extendidos de una categoria",
            description = "Devuelve detalles adicionales de una categoria especifica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Detalles de la categoria retornados correctamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    @GetMapping("/{id}/details")
    public ResponseEntity<CategoryDTO> getCategoryDetails(
            @Parameter(description = "ID de la categoria", required = true)
            @PathVariable Long id) {
        try {
            CategoryDTO dto = categoryService.getCategoryDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

