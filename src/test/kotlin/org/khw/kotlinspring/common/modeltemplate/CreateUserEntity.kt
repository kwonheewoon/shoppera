package org.khw.kotlinspring.common.modeltemplate

import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.user.domain.dto.AddressSaveDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.entity.Address
import org.khw.kotlinspring.user.domain.entity.UserEntity
import java.time.LocalDate

class CreateUserEntity {

    companion object{
        fun UserEntitySuccessCreate() : UserEntity {
            return UserEntity("권희운", "gmldns46", LocalDate.of(1997,7,24),
            Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
    }
}