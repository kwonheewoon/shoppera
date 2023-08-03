package org.khw.kotlinspring.common.factory.user

import org.khw.kotlinspring.user.domain.entity.Address
import org.khw.kotlinspring.user.domain.entity.UserEntity
import java.time.LocalDate

class CreateUserEntity {

    companion object{
        fun findSuccessCreate() : UserEntity {
            return UserEntity(1L, "권희운", "gmldns46", LocalDate.of(1997,7,24),
                    Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                    "010-7372-1474"
            )
        }
        fun saveSuccessCreate() : UserEntity {
            return UserEntity(1L,"권희운", "gmldns46", LocalDate.of(1997,7,24),
            Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }

        fun updateSuccessCreate() : UserEntity {
            return UserEntity(1L,"권희운", "gmldns46", LocalDate.of(1997,7,24),
                Address("인천서구 완정동", "힐스테이트1차 208동 1102", "77777"),
                "010-7372-1111"
            )
        }
    }
}