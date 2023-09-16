package org.khw.kotlinspring.common.enums

import org.springframework.http.HttpStatus

enum class ResCode(val code: String, val message: String, val httpStatus: HttpStatus) {

    // 200 Ok

    USERS_SAVE("200_100", "회원 정보가 등록 되었습니다.", HttpStatus.OK),
    USERS_UPDATE("200_101", "회원 정보가 수정 되었습니다.", HttpStatus.OK),
    USERS_DELETE("200_102", "회원 정보가 삭제 되었습니다.", HttpStatus.OK),
    USERS_FIND("200_103", "회원 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),
    USERS_PASSWORD_UPDATE("200_104", "패스워드 변경이 완료 되었습니다.", HttpStatus.OK),

    AUTHORITIES_SAVE("200_200", "권한 정보가 등록 되었습니다.", HttpStatus.OK),
    AUTHORITIES_DELETE("200_201", "권한 정보 삭제가 되었습니다.", HttpStatus.OK),


    ITEM_SAVE("200_400", "아이템 정보가 등록 되었습니다.", HttpStatus.OK),
    ITEM_UPDATE("200_401", "아이템 정보가 수정 되었습니다.", HttpStatus.OK),
    ITEM_DELETE("200_402", "아이템 정보가 삭제 되었습니다.", HttpStatus.OK),
    ITEM_FIND("200_403", "아이템 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),

    ITEM_TYPE_SAVE("200_404", "아이템 타입 정보가 등록 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_UPDATE("200_405", "아이템 타입 정보가 수정 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_DELETE("200_406", "아이템 타입 정보가 삭제 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_FIND("200_407", "아이템 타입 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),


    // 400 Bad Request

    // 409 Conflict

    DUPLICATE_AUTHORITIES("409_001", "중복된 권한 정보 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_USER("409_002", "중복된 계정입니다.", HttpStatus.CONFLICT),
    DUPLICATE_ITEM_TYPE("409_003", "중복된 아이템 타입 코드 입니다.", HttpStatus.CONFLICT),



    // 404 Not Found
    NOT_FOUND_CATEGORY("404_001", "존재하지 않는 카테고리 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM("404_002", "존재하지 않는 아이템 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER("404_003", "잘못된 회원 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITY("404_004", "존재하지 않는 권한 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITIES("404_005", "존재하지 않는 권한 정보 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM_TYPE("404_006", "존재하지 않는 아이템 타입 정보입니다.", HttpStatus.NOT_FOUND)

}