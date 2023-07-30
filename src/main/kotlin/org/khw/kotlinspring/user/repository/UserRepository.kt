package org.khw.kotlinspring.user.repository

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    /*
    * AccountId, 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByAccountIdAndDeleteFlag(accountId: String, deleteFlag: FlagYn) : Optional<UserEntity>

    /*
    * UserId, AccountId 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByIdAndAccountIdAndDeleteFlag(id : Long, accountId: String, deleteFlag: FlagYn) : Optional<UserEntity>

    /*
    * UserId, 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByIdAndDeleteFlag(id : Long, deleteFlag: FlagYn) : Optional<UserEntity>

}