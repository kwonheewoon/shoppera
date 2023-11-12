package org.khw.shoppera.brand.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.dto.BrandUpdateApiDto
import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.brand.service.BrandService
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.common.response.ErrCommonResponse
import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Tag(name = "Brand RestController", description = "브랜드 관련 API")
class BrandRestController(
    val brandService: BrandService
) {

    @PostMapping
    @Operation(summary = "브랜드 등록", description = "브랜드의 정보를 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_412",
            description = "브랜드 정보가 등록 되었습니다.",
            content = [Content(schema = Schema(implementation = BrandViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_005",
            description = "중복된 브랜드 이름 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun saveBrand(@RequestBody brandSaveApiDto: BrandSaveApiDto): ResponseEntity<CommonResponse<BrandViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_SAVE, brandService.saveBrand(brandSaveApiDto)))
    }

    @PutMapping("/{brandId}")
    @Operation(summary = "브랜드 수정", description = "브랜드의 정보를 수정 한다.", responses = [
        ApiResponse(
            responseCode = "200_413",
            description = "브랜드 정보가 수정 되었습니다.",
            content = [Content(schema = Schema(implementation = BrandViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "409_005",
            description = "중복된 브랜드 이름 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun updateBrand(@PathVariable brandId: Long, @RequestBody brandUpdateApiDto: BrandUpdateApiDto): ResponseEntity<CommonResponse<BrandViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_UPDATE, brandService.updateBrand(brandId, brandUpdateApiDto)))
    }

    @DeleteMapping("/{brandId}")
    @Operation(summary = "브랜드 수정", description = "브랜드의 정보를 삭제 한다.", responses = [
        ApiResponse(
            responseCode = "200_414",
            description = "브랜드 정보가 삭제 되었습니다.",
            content = [Content(schema = Schema(implementation = BrandViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun deleteBrand(@PathVariable brandId: Long): ResponseEntity<CommonResponse<Unit>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_DELETE, brandService.deleteBrand(brandId)))
    }
}