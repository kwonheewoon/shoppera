package org.khw.shoppera.itemoption.repository

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.domain.entity.ItemOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemOptionRepository : JpaRepository <ItemOption, Long>{

    /**
     * 삭제 여부에 따른 아이템 옵션 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<ItemOption>

    /**
     * 아이템, 삭제 여부에 따른 아이템 옵션 다건 조회
     *
     * @param item 아이템 정보
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByItemAndDeleteFlag(item: Item, deleteFlag: FlagYn) : List<ItemOption>

}