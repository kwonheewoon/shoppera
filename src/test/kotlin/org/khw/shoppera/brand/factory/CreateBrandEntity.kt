package org.khw.shoppera.brand.factory

import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn

class CreateBrandEntity {
    companion object{
        fun savedBrand(brandSaveApiDto: BrandSaveApiDto): Brand{
            return Brand(1L, brandSaveApiDto.name, brandSaveApiDto.explanation, brandSaveApiDto.foundedYear, FlagYn.N)
        }

        fun findBrand(): Brand{
            return Brand(1L, "Chanel", "샤넬 브랜드 설명입니다.", 1924)
        }
    }
}