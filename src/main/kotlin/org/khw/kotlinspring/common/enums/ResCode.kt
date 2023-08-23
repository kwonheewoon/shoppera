package org.khw.kotlinspring.common.enums

import org.springframework.http.HttpStatus

enum class ResCode(val code: String, val message: String, val httpStatus: HttpStatus) {

    // 200 Ok
    AUTHORITIES_SAVE("200_200", "권한 정보가 등록 되었습니다.", HttpStatus.OK),
    AUTHORITIES_DELETE("200_201", "권한 정보 삭제가 되었습니다.", HttpStatus.OK),


    // 400 Bad Request

    // 409 Conflict
    DUPLICATE_USER("409_300", "중복된 계정입니다.", HttpStatus.CONFLICT),


    // 404 Not Found
    NOT_FOUND_CATEGORY("404_001", "존재하지 않는 카테고리 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM("404_002", "존재하지 않는 아이템 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER("404_003", "존재하지 않는 유저 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITY("404_004", "존재하지 않는 권한 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITIES("404_005", "존재하지 않는 권한 정보 입니다.", HttpStatus.NOT_FOUND)
}