package org.khw.kotlinspring.common.modeltemplate

import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.user.domain.dto.AddressSaveDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import java.time.LocalDate

class CreateUserDto {

    companion object{
        fun UserSaveDtoSuccessCreate() : UserSaveDto{
            return UserSaveDto("권희운", "gmldns46", LocalDate.of(1997,7,24),
            AddressSaveDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
        fun UserSaveDtoFailCreate() : UserSaveDto{
            return UserSaveDto("", "gmldns46", LocalDate.of(1997,7,24),
                AddressSaveDto("인천서구 마전동", "힐스테이트2차 208동 1102", "666565"),
                "010-7372-1474"
            )
        }
    }
}