package org.khw.shoppera.brand.domain.entity

import org.khw.shoppera.brand.domain.dto.BrandSaveApiDto

class BrandFactory {

    companion object {
        fun createBrandEntity(brandSaveApiDto: BrandSaveApiDto): Brand{
            return Brand(null, brandSaveApiDto.name, brandSaveApiDto.explanation, brandSaveApiDto.foundedYear, brandSaveApiDto.displayFlag)
        }
    }
}