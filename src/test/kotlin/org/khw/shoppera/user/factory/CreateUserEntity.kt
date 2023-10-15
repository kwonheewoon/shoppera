package org.khw.shoppera.common.factory.user

import org.khw.shoppera.user.domain.entity.Address
import org.khw.shoppera.user.domain.entity.UserEntity
import java.time.LocalDate

class CreateUserEntity {

    companion object{
        fun saveUserEntity(): UserEntity{
            return UserEntity(null,"권희운", "gmldns46", "권희운", LocalDate.of(1997,7,24),
                Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474", null
            )
        }

        fun findSuccessCreate() : UserEntity {
            return UserEntity(1L, "권희운", "gmldns46", "\$2a\$10\$9xnv/5N67pIo2ppDLEyWwumb2kQe3TX4tvSt.t8mQKlRsUo6eQVci", LocalDate.of(1997,7,24),
                    Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                    "010-7372-1474", null
            )
        }
        fun savedSuccessCreate() : UserEntity {
            return UserEntity(1L,"권희운", "gmldns46", "\$2a\$10\$9xnv/5N67pIo2ppDLEyWwumb2kQe3TX4tvSt.t8mQKlRsUo6eQVci", LocalDate.of(1997,7,24),
            Address("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474", null
            )
        }

        fun updateSuccessCreate() : UserEntity {
            return UserEntity(1L,"권희운", "gmldns46", "\$2a\$10\$9xnv/5N67pIo2ppDLEyWwumb2kQe3TX4tvSt.t8mQKlRsUo6eQVci", LocalDate.of(1997,7,24),
                Address("인천서구 완정동", "힐스테이트1차 208동 1102", "77777"),
                "010-7372-1111", null
            )
        }
    }
}