package org.khw.shoppera.common.factory.user

import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.user.domain.dto.*
import java.time.LocalDate

class CreateUserDto {

    companion object{
        fun UserSaveDtoSuccessCreate() : UserSaveDto{
            return UserSaveDto("권희운", "gmldns46", "권희운", LocalDate.of(1997,7,24),
            AddressSaveDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
        fun UserUpdateDtoSuccessCreate() : UserUpdateDto{
            return UserUpdateDto("권희운", "gmldns46",
                AddressUpdateDto("인천서구 완정동", "힐스테이트1차 208동 1102", "77777"),
                "010-7372-1111"
            )
        }
        fun UserSaveDtoFailCreate() : UserSaveDto{
            return UserSaveDto("", "gmldns46", "권희운", LocalDate.of(1997,7,24),
                AddressSaveDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
        fun UserApiDtoCreate() : UserApiDto{
            return UserApiDto(1,"권희운", "gmldns46", LocalDate.of(1997,7,24),
                AddressApiDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
        fun UserApiDtoCreateOfDelete() : UserApiDto{
            return UserApiDto(1,"권희운", "gmldns46", LocalDate.of(1997,7,24),
                AddressApiDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474", CommonEnum.FlagYn.Y
            )
        }
    }
}