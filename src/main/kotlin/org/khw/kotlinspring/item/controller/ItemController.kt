package org.khw.kotlinspring.item.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.service.ItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
class ItemController(val itemService: ItemService) {

    @PostMapping
    fun saveItem(@RequestBody itemSaveDto: ItemSaveDto): ItemViewApiDto{
        return itemService.saveItem(itemSaveDto)
    }

    @PutMapping("/{itemId}")
    fun updateItem(@PathVariable itemId: Long, @RequestBody itemUpdateDto: ItemUpdateDto): ItemViewApiDto{
        return itemService.updateItem(itemId, itemUpdateDto)
    }
}