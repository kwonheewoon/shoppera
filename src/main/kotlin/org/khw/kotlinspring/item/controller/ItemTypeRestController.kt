package org.khw.kotlinspring.item.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.response.CommonResponse
import org.khw.kotlinspring.item.domain.dto.*
import org.khw.kotlinspring.item.service.ItemTypeService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item-types")
@RequiredArgsConstructor
class ItemTypeRestController(val itemTypeService: ItemTypeService) {

    @GetMapping("/{itemTypeId}")
    fun findItem(@PathVariable itemTypeId: Long): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_FIND, itemTypeService.findItemTypeByitemTypeId(itemTypeId)))
    }

    @PostMapping
    fun saveItem(@RequestBody itemTypeSaveDto: ItemTypeSaveDto): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_SAVE, itemTypeService.saveItemType(itemTypeSaveDto)))
    }

    @PutMapping("/{itemTypeId}")
    fun findItem(@PathVariable itemTypeId: Long, @RequestBody itemTypeUpdateDto: ItemTypeUpdateDto): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_UPDATE, itemTypeService.updateItemType(itemTypeId, itemTypeUpdateDto)))
    }

    @DeleteMapping("/{itemTypeId}")
    fun deleteItem(@PathVariable itemTypeId: Long): ResponseEntity<CommonResponse<Unit>>{

        itemTypeService.deleteItemType(itemTypeId)

        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_DELETE))
    }

}