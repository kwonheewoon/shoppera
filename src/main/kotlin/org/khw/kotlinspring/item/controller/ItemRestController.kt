package org.khw.kotlinspring.item.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.response.CommonResponse
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
    fun findItem(@PathVariable itemId: Long): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_FIND, itemService.findItem(itemId)))
    }

    @GetMapping
    fun findAllItem(@RequestParam categoryId: Long): ResponseEntity<CommonResponse<List<ItemViewApiDto>>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_FIND, itemService.findAllItem(categoryId)))
    }

    @PostMapping
    fun saveItem(@RequestBody itemSaveDto: ItemSaveDto): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_SAVE, itemService.saveItem(itemSaveDto)))
    }

    @PutMapping("/{itemId}")
    fun updateItem(@PathVariable itemId: Long, @RequestBody itemUpdateDto: ItemUpdateDto): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_UPDATE, itemService.updateItem(itemId, itemUpdateDto)))
    }

    @DeleteMapping("/{itemId}")
    fun deleteItem(@PathVariable itemId: Long): ResponseEntity<CommonResponse<Unit>>{

        itemService.deleteItem(itemId)

        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_DELETE))
    }

}