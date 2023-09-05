package org.khw.kotlinspring.item.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.response.CommonResponse
import org.khw.kotlinspring.item.domain.dto.*
import org.khw.kotlinspring.item.service.ItemService
import org.khw.kotlinspring.item.service.ItemTypeService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item-types")
@RequiredArgsConstructor
class ItemTypeRestController(val itemTypeService: ItemTypeService) {

    @GetMapping("/{typeCode}")
    fun findItem(@PathVariable typeCode: String): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_FIND, itemTypeService.findItemTypeByTypeCode(typeCode)))
    }

    @PostMapping
    fun saveItem(@RequestBody itemTypeSaveDto: ItemTypeSaveDto): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_SAVE, itemTypeService.saveItemType(itemTypeSaveDto)))
    }

//    @PutMapping("/{itemId}")
//    fun updateItem(@PathVariable itemId: Long, @RequestBody itemUpdateDto: ItemUpdateDto): ResponseEntity<CommonResponse<ItemViewApiDto>>{
//        return ResponseEntity.ok()
//            .headers(HttpHeaders())
//            .body(CommonResponse(ResCode.ITEM_UPDATE, itemService.updateItem(itemId, itemUpdateDto)))
//    }
//
//    @DeleteMapping("/{itemId}")
//    fun deleteItem(@PathVariable itemId: Long): ResponseEntity<CommonResponse<Unit>>{
//
//        itemService.deleteItem(itemId)
//
//        return ResponseEntity.ok()
//            .headers(HttpHeaders())
//            .body(CommonResponse(ResCode.ITEM_DELETE))
//    }

}