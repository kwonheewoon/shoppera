package org.khw.shoppera.brand.domain.mapper


import org.khw.shoppera.brand.domain.dto.BrandViewApiDto
import org.khw.shoppera.brand.domain.entity.Brand
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BrandMapper {


    fun entityToViewApiDto(brand: Brand): BrandViewApiDto

    fun entityListToViewApiDtoList(brandList: List<Brand>): List<BrandViewApiDto>
}