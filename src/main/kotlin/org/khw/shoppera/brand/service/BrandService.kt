package org.khw.shoppera.brand.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.brand.domain.entity.BrandFactory
import org.khw.shoppera.brand.domain.mapper.BrandMapper
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class BrandService(
    val brandMapper: BrandMapper,
    val brandRepository: BrandRepository
) {

    fun saveBrand(brandSaveApiDto: BrandSaveApiDto): BrandViewApiDto{
        brandRepository.findByNameAndDeleteFlag(brandSaveApiDto.name, FlagYn.N).ifPresent { throw BrandException(ResCode.DUPLICATE_BRAND_NAME) }
        val brandSavedEntity = BrandFactory.createBrandEntity(brandSaveApiDto)

        return brandMapper.entityToViewApiDto(brandRepository.save(brandSavedEntity))
    }
}