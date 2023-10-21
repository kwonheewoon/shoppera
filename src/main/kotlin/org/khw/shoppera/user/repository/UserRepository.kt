package org.khw.shoppera.user.repository

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    /*
    * AccountId, 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByAccountIdAndDeleteFlag(accountId: String, deleteFlag: FlagYn) : Optional<User>

    /*
    * UserId, AccountId 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByIdAndAccountIdAndDeleteFlag(id : Long, accountId: String, deleteFlag: FlagYn) : Optional<User>

    /*
    * UserId, 삭제 여부 기준 User 단건 조회
    *
    * */
    fun findByIdAndDeleteFlag(id : Long, deleteFlag: FlagYn) : Optional<User>

}