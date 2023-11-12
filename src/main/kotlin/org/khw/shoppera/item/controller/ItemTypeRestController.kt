package org.khw.shoppera.item.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.common.response.ErrCommonResponse
import org.khw.shoppera.item.domain.dto.*
import org.khw.shoppera.item.service.ItemTypeService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item-types")
@RequiredArgsConstructor
@Tag(name = "ItemType RestController", description = "아이템 타입 관련 API")
class ItemTypeRestController(val itemTypeService: ItemTypeService) {

    @GetMapping("/{itemTypeId}")
    @Operation(summary = "아이템 타입 단건 조회", description = "아이템 타입의 정보를 단건 조회 한다.", responses = [
        ApiResponse(
            responseCode = "200_407",
            description = "아이템 타입 정보가 정상 적으로 조회 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun findItem(@PathVariable itemTypeId: Long): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_FIND, itemTypeService.findItemTypeByitemTypeId(itemTypeId)))
    }

    @PostMapping
    @Operation(summary = "아이템 타입 등록", description = "아이템 타입의 정보를 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_404",
            description = "아이템 타입 정보가 등록 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_003",
            description = "중복된 아이템 타입 코드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun saveItem(@RequestBody itemTypeSaveDto: ItemTypeSaveDto): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_SAVE, itemTypeService.saveItemType(itemTypeSaveDto)))
    }

    @PutMapping("/{itemTypeId}")
    @Operation(summary = "아이템 타입 수정", description = "아이템 타입의 정보를 수정 한다.", responses = [
        ApiResponse(
            responseCode = "200_405",
            description = "아이템 타입 정보가 수정 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_003",
            description = "중복된 아이템 타입 코드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun findItem(@PathVariable itemTypeId: Long, @RequestBody itemTypeUpdateDto: ItemTypeUpdateDto): ResponseEntity<CommonResponse<ItemTypeViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_UPDATE, itemTypeService.updateItemType(itemTypeId, itemTypeUpdateDto)))
    }

    @DeleteMapping("/{itemTypeId}")
    @Operation(summary = "아이템 타입 등록", description = "아이템 타입의 정보를 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_406",
            description = "아이템 타입 정보가 삭제 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun deleteItem(@PathVariable itemTypeId: Long): ResponseEntity<CommonResponse<Unit>>{

        itemTypeService.deleteItemType(itemTypeId)

        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_TYPE_DELETE))
    }

}