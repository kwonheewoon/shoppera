package org.khw.kotlinspring.item.repository

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemTypeRepository : JpaRepository <ItemTypeEntity, Long>{

    /**
     * 아이템 타입 아이디, 삭제 여부에 따른 아이템 타입 단일 조회
     *
     * @param id 아이템 타입 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<ItemTypeEntity>

    /**
     * 아이템 코드, 삭제 여부에 따른 아이템 타입 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByTypeCodeAndDeleteFlag(typeCode: String, deleteFlag: FlagYn) : Optional<ItemTypeEntity>

    /**
     * 아이디, 아이템 코드, 삭제 여부에 따른 아이템 타입 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId: Long, typeCode: String, deleteFlag: FlagYn) : Optional<ItemTypeEntity>

}