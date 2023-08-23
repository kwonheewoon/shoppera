package org.khw.kotlinspring.item.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.service.ItemService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
class ItemRestController(val itemService: ItemService) {

    @GetMapping("/{itemId}")
    fun findItem(@PathVariable itemId: Long): ResponseEntity<ItemViewApiDto>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(itemService.findItem(itemId))
    }

    @GetMapping
    fun findAllItem(@RequestParam categoryId: Long): ResponseEntity<List<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(itemService.findAllItem(categoryId))
    }

    @PostMapping
    fun saveItem(@RequestBody itemSaveDto: ItemSaveDto): ResponseEntity<ItemViewApiDto>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(itemService.saveItem(itemSaveDto))
    }

    @PutMapping("/{itemId}")
    fun updateItem(@PathVariable itemId: Long, @RequestBody itemUpdateDto: ItemUpdateDto): ResponseEntity<ItemViewApiDto>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(itemService.updateItem(itemId, itemUpdateDto))
    }
}