package org.khw.shoppera.brand.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.brand.domain.entity.BrandFactory
import org.khw.shoppera.brand.domain.mapper.BrandMapper
import org.khw.shoppera.brand.factory.CreateBrandDto
import org.khw.shoppera.brand.factory.CreateBrandEntity
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class BrandServiceTest {

    @Mock
    lateinit var brandRepository: BrandRepository

    @Mock
    lateinit var brandMapper: BrandMapper

    @InjectMocks
    lateinit var brandService: BrandService

    @Test
    fun `브랜드 등록 실패(중복 브랜드 이름)`(){
        // Given
        val brandSaveApiDto = CreateBrandDto.brandSaveApiDto()
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByNameAndDeleteFlag(brandSaveApiDto.name, FlagYn.N))
            .willReturn(Optional.of(findBrand))

        // When
        val throwable = assertThrows(BrandException::class.java){
            brandService.saveBrand(brandSaveApiDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `브랜드 등록 성공`(){
        // Given
        val brandSaveApiDto = CreateBrandDto.brandSaveApiDto()
        val brandViewApiDto = CreateBrandDto.brandViewApiDto()
        val saveBrand = BrandFactory.createBrandEntity(brandSaveApiDto)
        val savedBrand = CreateBrandEntity.savedBrand(brandSaveApiDto)

        given(brandRepository.findByNameAndDeleteFlag(brandSaveApiDto.name, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.save(any(Brand::class.java)))
            .willReturn(savedBrand)
        given(brandMapper.entityToViewApiDto(savedBrand))
            .willReturn(brandViewApiDto)

        // When®
        val result = brandService.saveBrand(brandSaveApiDto)

        // Then
        assertEquals(result, brandViewApiDto)
        assertEquals(result.name, brandViewApiDto.name)
        assertEquals(result.explanation, brandViewApiDto.explanation)
        assertEquals(result.foundedYear, brandViewApiDto.foundedYear)
        verify(brandRepository).findByNameAndDeleteFlag(brandSaveApiDto.name, FlagYn.N)
        verify(brandRepository).save(any(Brand::class.java))
        verify(brandMapper).entityToViewApiDto(savedBrand)
    }
}