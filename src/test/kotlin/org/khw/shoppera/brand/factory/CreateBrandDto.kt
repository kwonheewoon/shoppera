package org.khw.shoppera.brand.factory

import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto
import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.common.enums.CommonEnum.FlagYn

class CreateBrandDto {
    companion object{
        fun brandSaveApiDto(): BrandSaveApiDto{
            return BrandSaveApiDto("Chanel", "샤넬 브랜드 설명입니다.", 1924, FlagYn.N)
        }

        fun brandViewApiDto(): BrandViewApiDto{
            return BrandViewApiDto(1L, "Chanel", "샤넬 브랜드 설명입니다.", 1924, FlagYn.N)
        }
    }
}