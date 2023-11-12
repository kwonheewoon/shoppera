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
import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.service.ItemService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Item RestController", description = "아이템 관련 API")
class ItemRestController(val itemService: ItemService) {

    @GetMapping("/{itemId}")
    @Operation(summary = "아이템 단건 조회", description = "아이템의 정보를 단건 조회 한다.", responses = [
        ApiResponse(
            responseCode = "200_403",
            description = "아이템 정보가 정상 적으로 조회 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_002",
            description = "존재 하지 않는 아이템 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun findItem(@PathVariable itemId: Long): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_FIND, itemService.findItem(itemId)))
    }

    @GetMapping
    @Operation(summary = "카테고리에 따른 아이템 전체 조회", description = "카테고리 하위에 위치한 아이템의 전체를 조회 한다.", responses = [
        ApiResponse(
            responseCode = "200_403",
            description = "아이템 정보가 정상 적으로 조회 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_001",
            description = "존재 하지 않는 카테고리 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun findAllItem(@RequestParam categoryId: Long): ResponseEntity<CommonResponse<List<ItemViewApiDto>>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_FIND, itemService.findAllItem(categoryId)))
    }

    @PostMapping
    @Operation(summary = "아이템 등록", description = "아이템의 정보를 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_400",
            description = "아이템 정보가 등록 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_001",
            description = "존재 하지 않는 카테고리 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun saveItem(@RequestBody itemSaveDto: ItemSaveDto): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_SAVE, itemService.saveItem(itemSaveDto)))
    }

    @PutMapping("/{itemId}")
    @Operation(summary = "아이템 수정", description = "아이템의 정보를 수정 한다.", responses = [
        ApiResponse(
            responseCode = "200_401",
            description = "아이템 정보가 수정 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_001",
            description = "존재 하지 않는 카테고리 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_002",
            description = "존재 하지 않는 아이템 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
    ])
    fun updateItem(@PathVariable itemId: Long, @RequestBody itemUpdateDto: ItemUpdateDto): ResponseEntity<CommonResponse<ItemViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_UPDATE, itemService.updateItem(itemId, itemUpdateDto)))
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "아이템 삭제", description = "아이템의 정보를 삭제 한다.", responses = [
        ApiResponse(
            responseCode = "200_402",
            description = "아이템 정보가 삭제 되었습니다.",
            content = [Content(schema = Schema(implementation = ItemViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_006",
            description = "존재 하지 않는 아이템 타입 정보입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun deleteItem(@PathVariable itemId: Long): ResponseEntity<CommonResponse<Unit>>{

        itemService.deleteItem(itemId)

        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ITEM_DELETE))
    }

}