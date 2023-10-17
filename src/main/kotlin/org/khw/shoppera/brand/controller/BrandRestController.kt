package org.khw.shoppera.brand.controller

import lombok.RequiredArgsConstructor
import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.dto.BrandUpdateApiDto
import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.brand.service.BrandService
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
class BrandRestController(
    val brandService: BrandService
) {

    @PostMapping
    fun saveBrand(@RequestBody brandSaveApiDto: BrandSaveApiDto): ResponseEntity<CommonResponse<BrandViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_SAVE, brandService.saveBrand(brandSaveApiDto)))
    }

    @PutMapping("/{brandId}")
    fun updateBrand(@PathVariable brandId: Long, @RequestBody brandUpdateApiDto: BrandUpdateApiDto): ResponseEntity<CommonResponse<BrandViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_UPDATE, brandService.updateBrand(brandId, brandUpdateApiDto)))
    }

    @DeleteMapping("/{brandId}")
    fun deleteBrand(@PathVariable brandId: Long): ResponseEntity<CommonResponse<Unit>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.BRAND_DELETE, brandService.deleteBrand(brandId)))
    }
}