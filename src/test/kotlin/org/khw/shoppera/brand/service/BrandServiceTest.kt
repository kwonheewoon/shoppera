package org.khw.shoppera.brand.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*
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
        val brandViewApiDto = CreateBrandDto.brandViewApiDto(brandSaveApiDto.name, brandSaveApiDto.explanation, brandSaveApiDto.foundedYear)
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

    @Test
    fun `브랜드 수정 실패(중복 브랜드 이름)`(){
        // Given
        val brandId = 1L
        val brandUpdateApiDto = CreateBrandDto.brandUpdateApiDto()
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByIdNotAndNameAndDeleteFlag(brandId, brandUpdateApiDto.name, FlagYn.N))
            .willReturn(Optional.of(findBrand))

        // When
        val throwable = assertThrows(BrandException::class.java){
            brandService.updateBrand(brandId, brandUpdateApiDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_BRAND_NAME.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `브랜드 수정 성공`(){
        // Given
        val brandId = 1L
        val brandUpdateApiDto = CreateBrandDto.brandUpdateApiDto()
        val brandViewApiDto = CreateBrandDto.brandViewApiDto(brandUpdateApiDto.name, brandUpdateApiDto.explanation, brandUpdateApiDto.foundedYear)
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByIdNotAndNameAndDeleteFlag(brandId, brandUpdateApiDto.name, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.findByIdAndDeleteFlag(brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(brandMapper.entityToViewApiDto(findBrand))
            .willReturn(brandViewApiDto)

        // When®
        val result = brandService.updateBrand(brandId, brandUpdateApiDto)

        // Then
        assertEquals(result, brandViewApiDto)
        assertEquals(result.name, brandViewApiDto.name)
        assertEquals(result.explanation, brandViewApiDto.explanation)
        assertEquals(result.foundedYear, brandViewApiDto.foundedYear)
        assertNotEquals(findBrand.explanation, brandViewApiDto.explanation)
        assertNotEquals(findBrand.foundedYear, brandViewApiDto.foundedYear)
        verify(brandRepository).findByIdNotAndNameAndDeleteFlag(brandId, brandUpdateApiDto.name, FlagYn.N)
        verify(brandRepository).findByIdAndDeleteFlag(brandId, FlagYn.N)
        verify(brandMapper).entityToViewApiDto(findBrand)
    }

    @Test
    fun `브랜드 삭제 실패(존재하지 않는 브랜드)`(){
        // Given
        val brandId = 1L

        given(brandRepository.findByIdAndDeleteFlag(brandId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(BrandException::class.java){
            brandService.deleteBrand(brandId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_BRAND.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_BRAND.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_BRAND.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `브랜드 삭제 성공`(){
        // Given
        val brandId = 1L
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByIdAndDeleteFlag(brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))

        // When
        brandService.deleteBrand(brandId)

        // Then
        assertEquals(findBrand.deleteFlag, FlagYn.Y)
        verify(brandRepository).findByIdAndDeleteFlag(brandId, FlagYn.N)
    }
}