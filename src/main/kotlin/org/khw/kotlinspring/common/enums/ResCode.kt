package org.khw.kotlinspring.common.enums

import org.springframework.http.HttpStatus

enum class ResCode(val code: String, val message: String, val httpStatus: HttpStatus) {

    // 200

    // 400

    // 404
    NOT_FOUND_CATEGORY("404_001", "존재하지 않는 카테고리 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM("404_002", "존재하지 않는 아이템 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER("404_003", "존재하지 않는 유저 정보입니다.", HttpStatus.NOT_FOUND)
}