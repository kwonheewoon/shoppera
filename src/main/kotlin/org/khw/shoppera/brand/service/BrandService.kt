package org.khw.shoppera.brand.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.dto.BrandUpdateApiDto
import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.brand.domain.entity.BrandFactory
import org.khw.shoppera.brand.domain.mapper.BrandMapper
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class BrandService(
    val brandMapper: BrandMapper,
    val brandRepository: BrandRepository
) {

    @Transactional
    fun saveBrand(brandSaveApiDto: BrandSaveApiDto): BrandViewApiDto{
        brandRepository.findByNameAndDeleteFlag(brandSaveApiDto.name, FlagYn.N).ifPresent { throw BrandException(ResCode.DUPLICATE_BRAND_NAME) }
        val brandSavedEntity = BrandFactory.createBrandEntity(brandSaveApiDto)

        return brandMapper.entityToViewApiDto(brandRepository.save(brandSavedEntity))
    }

    @Transactional
    fun updateBrand(brandId: Long, brandUpdateApiDto: BrandUpdateApiDto): BrandViewApiDto{
        brandRepository.findByIdNotAndNameAndDeleteFlag(brandId, brandUpdateApiDto.name, FlagYn.N).ifPresent { throw BrandException(ResCode.DUPLICATE_BRAND_NAME) }
        val findBrand = brandRepository.findByIdAndDeleteFlag(brandId, FlagYn.N).orElseThrow { throw BrandException(ResCode.NOT_FOUND_BRAND) }

        findBrand.update(brandUpdateApiDto)

        return brandMapper.entityToViewApiDto(findBrand)
    }

    @Transactional
    fun deleteBrand(brandId: Long){
        val findBrand = brandRepository.findByIdAndDeleteFlag(brandId, FlagYn.N).orElseThrow { throw BrandException(ResCode.NOT_FOUND_BRAND) }

        findBrand.delete()
    }
}