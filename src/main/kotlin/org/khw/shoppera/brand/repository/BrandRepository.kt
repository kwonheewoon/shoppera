package org.khw.shoppera.brand.repository

import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BrandRepository : JpaRepository <Brand, Long>{

    /**
     * 삭제 여부에 따른 브랜드 단일 조회
     *
     * @param id 브랜드 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<Brand>

    /**
     * 브랜드 이름, 삭제 여부에 따른 브랜드 단일 조회
     *
     * @param name 브랜드 이름
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByNameAndDeleteFlag(name: String, deleteFlag: FlagYn) : Optional<Brand>

    /**
     * 브랜드 설립년도, 삭제 여부에 따른 브랜드 단일 조회
     *
     * @param year 브랜드 설립년도
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByFoundedYearAndDeleteFlag(year: Int, deleteFlag: FlagYn) : List<Brand>

    /**
     * 브랜드 아이디, 브랜드 이름, 삭제 여부에 따른 브랜드 단일 조회
     *
     * @param name 브랜드 이름
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdNotAndNameAndDeleteFlag(id: Long, name: String, deleteFlag: FlagYn) : Optional<Brand>

}